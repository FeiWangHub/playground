import requests

class MCPClient:
    def __init__(self, base_url="http://localhost:8000"):
        self.base_url = base_url.rstrip("/")

    def list_tools(self):
        url = f"{self.base_url}/list_tools"
        resp = requests.post(url)
        resp.raise_for_status()
        return resp.json()

    def call_tool(self, name, arguments):
        url = f"{self.base_url}/call_tool"
        payload = {
            "name": name,
            "arguments": arguments
        }
        resp = requests.post(url, json=payload)
        resp.raise_for_status()
        return resp.json()

    def refresh_tools(self):
        url = f"{self.base_url}/api/refresh-tools"
        resp = requests.post(url)
        resp.raise_for_status()
        return resp.json()

if __name__ == "__main__":
    client = MCPClient("http://localhost:8000")

    # 获取工具列表
    tools = client.list_tools()
    print("工具列表：", tools)

    # 假设有一个工具叫 "summarize"，并且需要参数 {"text": "Harry Potter"}
    result = client.call_tool("summarize", {"text": "Harry Potter"})
    print("调用 summarize 工具结果：", result)

    # 刷新工具缓存（SSE模式下可用）
    refresh_result = client.refresh_tools()
    print("刷新工具缓存结果：", refresh_result) 