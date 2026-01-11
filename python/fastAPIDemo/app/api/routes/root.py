from app.core.config import settings
from app.core.response import ok
from app.schemas.response import ResponseDTO
from fastapi import APIRouter

router = APIRouter()


@router.get("/health", response_model=ResponseDTO)
def health():
    return ok({"status": "ok"})


@router.get("/info", response_model=ResponseDTO)
def info():
    return ok({"app_name": settings.app_name, "env": settings.env})
