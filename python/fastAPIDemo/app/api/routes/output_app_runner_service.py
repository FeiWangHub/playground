from app.core.http_response import ok
from app.schemas.dtos import DeploymentDTO
from app.schemas.http_resp_dto import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/deploy", response_model=ResponseDTO)
def deploy(name: str = Body(...)):
    return ok(DeploymentDTO(id="ar-001", name=name, status="deploying"))


@router.get("/deployments", response_model=ResponseDTO)
def deployments():
    return ok({"deployments": [DeploymentDTO(id="ar-001", status="deploying")]})


@router.get("/status", response_model=ResponseDTO)
def status(deployment_id: str):
    return ok(DeploymentDTO(id=deployment_id, status="mock-status"))
