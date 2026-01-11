from app.core.response import ok
from app.schemas.response import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/apps", response_model=ResponseDTO)
def create_app(name: str = Body(...)):
    return ok({"service": "outputStreamLitService", "id": "sl-001", "name": name})


@router.get("/apps", response_model=ResponseDTO)
def list_apps():
    return ok(
        {
            "service": "outputStreamLitService",
            "apps": [{"id": "sl-001", "name": "mock"}],
        }
    )


@router.get("/status", response_model=ResponseDTO)
def status(app_id: str):
    return ok({"service": "outputStreamLitService", "id": app_id, "status": "running"})
