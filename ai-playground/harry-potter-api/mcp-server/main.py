from fastapi import FastAPI, HTTPException
from fastapi.responses import RedirectResponse
import uvicorn
import requests
from typing import List, Optional

app = FastAPI(title="Harry Potter API Server")

BASE_URL = "https://hp-api.onrender.com/api"

@app.get("/")
async def root():
    """重定向到 API 文档页面"""
    return RedirectResponse(url="/docs")

@app.get("/characters")
async def get_characters():
    """获取所有角色信息"""
    try:
        response = requests.get(f"{BASE_URL}/characters")
        response.raise_for_status()
        return response.json()
    except requests.RequestException as e:
        raise HTTPException(status_code=500, detail=str(e))

@app.get("/characters/{index}")
async def get_character(index: int):
    """获取特定角色信息"""
    try:
        response = requests.get(f"{BASE_URL}/characters")
        response.raise_for_status()
        characters = response.json()
        if 0 <= index < len(characters):
            return characters[index]
        raise HTTPException(status_code=404, detail="Character not found")
    except requests.RequestException as e:
        raise HTTPException(status_code=500, detail=str(e))

@app.get("/spells")
async def get_spells():
    """获取所有咒语信息"""
    try:
        response = requests.get(f"{BASE_URL}/spells")
        response.raise_for_status()
        return response.json()
    except requests.RequestException as e:
        raise HTTPException(status_code=500, detail=str(e))

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000) 