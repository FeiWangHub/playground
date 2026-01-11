from app.core.http_response import ok
from app.schemas.dtos import GenerateResultDTO, ModelListDTO
from app.schemas.http_resp_dto import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.get("/status", response_model=ResponseDTO)
def status():
    return ok({"status": "ok"})


@router.get("/models", response_model=ResponseDTO)
def models():
    return ok(ModelListDTO(models=["gpt-mock", "llama-mock"]))


@router.post("/generate", response_model=ResponseDTO)
def generate(prompt: str = Body(...)):
    return ok(GenerateResultDTO(result=f"mock:{prompt}"))
