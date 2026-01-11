from app.core.http_response import ok
from app.schemas.dtos import AppDTO
from app.schemas.http_resp_dto import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/apps", response_model=ResponseDTO)
def create_app(name: str = Body(...)):
    return ok(AppDTO(id="sl-001", name=name))


@router.get("/apps", response_model=ResponseDTO)
def list_apps():
    return ok({"apps": [AppDTO(id="sl-001", name="mock")]})


@router.get("/status", response_model=ResponseDTO)
def status(app_id: str):
    return ok(AppDTO(id=app_id, name="mock", status="running"))
