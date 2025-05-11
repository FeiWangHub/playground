from fastapi import FastAPI, HTTPException
from fastapi.responses import RedirectResponse, StreamingResponse
from fastapi.middleware.cors import CORSMiddleware
import uvicorn
import requests
from typing import List, Optional
import json
import asyncio
import datetime

app = FastAPI(title="Harry Potter API Server")

# 添加 CORS 中间件
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

BASE_URL = "https://hp-api.onrender.com/api"

@app.get("/")
async def root():
    """重定向到 API 文档页面"""
    return RedirectResponse(url="/docs")

@app.get("/characters", response_class=StreamingResponse)
async def get_characters():
    """获取所有角色信息"""
    async def event_generator():
        try:
            response = requests.get(f"{BASE_URL}/characters")
            response.raise_for_status()
            data = response.json()
            yield f"data: {json.dumps(data)}\n\n"
        except requests.RequestException as e:
            yield f"data: {json.dumps({'error': str(e)})}\n\n"

    return StreamingResponse(
        event_generator(),
        media_type="text/event-stream"
    )

@app.get("/characters/{index}", response_class=StreamingResponse)
async def get_character(index: int):
    """获取特定角色信息"""
    async def event_generator():
        try:
            response = requests.get(f"{BASE_URL}/characters")
            response.raise_for_status()
            characters = response.json()
            if 0 <= index < len(characters):
                yield f"data: {json.dumps(characters[index])}\n\n"
            else:
                yield f"data: {json.dumps({'error': 'Character not found'})}\n\n"
        except requests.RequestException as e:
            yield f"data: {json.dumps({'error': str(e)})}\n\n"

    return StreamingResponse(
        event_generator(),
        media_type="text/event-stream"
    )

@app.get("/spells", response_class=StreamingResponse)
async def get_spells():
    """获取所有咒语信息"""
    async def event_generator():
        try:
            response = requests.get(f"{BASE_URL}/spells")
            response.raise_for_status()
            data = response.json()
            yield f"data: {json.dumps(data)}\n\n"
        except requests.RequestException as e:
            yield f"data: {json.dumps({'error': str(e)})}\n\n"

    return StreamingResponse(
        event_generator(),
        media_type="text/event-stream"
    )

@app.get("/sse", response_class=StreamingResponse)
async def sse_connection():
    """SSE 连接端点 - 提供服务器状态信息"""
    async def event_generator():
        try:
            while True:
                # 发送服务器状态信息
                status = {
                    "type": "status",
                    "data": {
                        "status": "online",
                        "timestamp": datetime.datetime.now().isoformat(),
                        "endpoints": {
                            "characters": f"{BASE_URL}/characters",
                            "spells": f"{BASE_URL}/spells"
                        }
                    }
                }
                yield f"data: {json.dumps(status)}\n\n"
                # 每5秒发送一次心跳
                await asyncio.sleep(5)
        except Exception as e:
            yield f"data: {json.dumps({'error': str(e)})}\n\n"

    return StreamingResponse(
        event_generator(),
        media_type="text/event-stream"
    )

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000) 