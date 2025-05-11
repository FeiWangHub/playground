from mcp.server.lowlevel import Server, NotificationOptions
import mcp.types as types
from mcp.server.sse import SseServerTransport
from mcp.server.stdio import stdio_server

from starlette.applications import Starlette
from starlette.requests import Request
from starlette.routing import Mount, Route
from starlette.responses import JSONResponse

from mcp.server.models import InitializationOptions
import uvicorn
import anyio
import json
import os
import time
from reacter_openapitools import AnthropicAdapter

# Load configuration from mcpconfig.json
def load_config():
    config_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "mcpconfig.json")
    try:
        with open(config_path, 'r') as f:
            return json.load(f)
    except FileNotFoundError:
        print(f"Configuration file not found at {config_path}")
        return {
            "server_name": "your-mcp-server",
            "host": "0.0.0.0",
            "port": 8000,
            "server_version": "0.1.0",
            "tool_names": [],
            "mode": "stdio",
            "refresh_time_minutes": 1440  # Default 24 hours
        }
    except json.JSONDecodeError:
        print(f"Error parsing configuration file at {config_path}")
        return {
            "server_name": "your-mcp-server",
            "host": "0.0.0.0",
            "port": 8000,
            "server_version": "0.1.0",
            "tool_names": [],
            "mode": "stdio",
            "refresh_time_minutes": 1440  # Default 24 hours
        }

# Load configuration
config = load_config()

# Create server with configured name
app = Server(config["server_name"])

# Create a state object to hold the adapter and tool handler
class AppState:
    def __init__(self):
        self.adapter = None
        self.tool_handler = None
        self.mcp_tools = []
        self.initialized = False
        self.last_refresh_time = 0
        self.refresh_time_minutes = config.get("refresh_time_minutes", 1440)  # Default to 24 hours
        self.auto_refresh_count = 50  # Refresh after 50 calls
        self.call_count = 0
    
    def should_refresh(self):
        # Check if it's time to refresh based on time elapsed or call count
        current_time = time.time()
        time_elapsed = (current_time - self.last_refresh_time) / 60  # Convert to minutes
        
        return (not self.initialized or 
                time_elapsed > self.refresh_time_minutes or 
                self.call_count >= self.auto_refresh_count)
    
    def refresh_tools(self):
        if config.get("api_key") is None:
            self.adapter = AnthropicAdapter(
                folder_path="./openapitools", 
                verbose=False
            )
        else:
            self.adapter = AnthropicAdapter(
                api_key=config["api_key"],
                verbose=False
            )
        self.adapter.set_environment_variables(config.get("envs",{}))
        tools = []
        if config["tool_names"] and len(config["tool_names"]) > 0:
            tools = self.adapter.get_anthropic_tools(config["tool_names"])
        else:
            tools = self.adapter.get_anthropic_tools()
        
        self.tool_handler = self.adapter.create_anthropic_tool_handler()
        
        self.mcp_tools = []
        for tool in tools:
            self.mcp_tools.append(
                types.Tool(
                    name=tool.get("name"),
                    description=tool.get("description"),
                    inputSchema=tool.get("input_schema"),
                )
            )
        self.initialized = True
        self.last_refresh_time = time.time()
        self.call_count = 0
        print(f"Tools refreshed at {time.strftime('%Y-%m-%d %H:%M:%S')}")

# Create and attach state to the app
app.state = AppState()

@app.list_tools()
async def list_tools() -> list[types.Tool]:
    # Check if tools should be refreshed
    if app.state.should_refresh():
        app.state.refresh_tools()
    return app.state.mcp_tools

@app.call_tool()
async def call_tool(
    name: str,
    arguments: dict
) -> list[types.TextContent | types.ImageContent | types.EmbeddedResource]:
    # Check if tools should be refreshed
    if app.state.should_refresh():
        app.state.refresh_tools()
    
    # Increment call count
    app.state.call_count += 1
    
    # Use the tool handler from state
    result = app.state.tool_handler({
        "id": "223",
        "name": name,
        "input": arguments,
    })
    
    return [types.TextContent(type="text", text=result.output)]

# Manual refresh endpoint for SSE mode
async def handle_refresh(request: Request) -> JSONResponse:
    app.state.refresh_tools()
    return JSONResponse({
        "success": True,
        "message": "Tools cache refreshed successfully",
        "timestamp": time.strftime("%Y-%m-%d %H:%M:%S")
    })

# SSE server setup
sse = SseServerTransport("/messages/")

async def handle_sse(request:Request) -> None:
    async with sse.connect_sse(request.scope, request.receive, request._send) as streams:
        await app.run(streams[0], streams[1], InitializationOptions(
            server_name=config["server_name"],
            server_version=config["server_version"],
            capabilities=app.get_capabilities(
                notification_options=NotificationOptions(),
                experimental_capabilities={}
            )
        ))
        
async def run_sse_async():
    starlette_app = Starlette(
        debug=True,
        routes=[
            Route("/sse", endpoint=handle_sse),
            Route("/api/refresh-tools", endpoint=handle_refresh, methods=["POST"]),
            Mount("/messages/", app=sse.handle_post_message),
        ]
    )
    config_obj = uvicorn.Config(
        starlette_app,
        host=config["host"],
        port=config["port"],
        log_level="info",   
    )

    server = uvicorn.Server(config_obj)
    await server.serve()
    
async def run_stdio_async():
    async with stdio_server() as (read_stream, write_stream):
        await app.run(read_stream, write_stream, InitializationOptions(
            server_name=config["server_name"],
            server_version=config["server_version"],
            capabilities=app.get_capabilities(
                notification_options=NotificationOptions(),
                experimental_capabilities={}
            )
        ))
    
def run():
    mode = config.get("mode", "stdio").lower()
    if mode == "stdio":
        anyio.run(run_stdio_async)
    elif mode == "sse":
        anyio.run(run_sse_async)
    else:
        raise ValueError(f"Unknown transport mode: {mode}")

if __name__ == "__main__":
    run()