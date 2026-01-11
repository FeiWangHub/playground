from app.core.response import ok
from app.schemas.response import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.get("/status", response_model=ResponseDTO)
def status():
    return ok({"service": "AIService", "status": "ok"})


@router.get("/models", response_model=ResponseDTO)
def models():
    return ok({"service": "AIService", "models": ["gpt-mock", "llama-mock"]})


@router.post("/generate", response_model=ResponseDTO)
def generate(prompt: str = Body(...)):
    return ok({"service": "AIService", "result": f"mock:{prompt}"})
