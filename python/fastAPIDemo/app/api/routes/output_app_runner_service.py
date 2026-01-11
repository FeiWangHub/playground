from app.core.response import ok
from app.schemas.response import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/deploy", response_model=ResponseDTO)
def deploy(name: str = Body(...)):
    return ok(
        {
            "service": "outputAppRunnerService",
            "deployment_id": "ar-001",
            "name": name,
            "status": "deploying",
        }
    )


@router.get("/deployments", response_model=ResponseDTO)
def deployments():
    return ok(
        {
            "service": "outputAppRunnerService",
            "deployments": [{"id": "ar-001", "status": "deploying"}],
        }
    )


@router.get("/status", response_model=ResponseDTO)
def status(deployment_id: str):
    return ok(
        {
            "service": "outputAppRunnerService",
            "deployment_id": deployment_id,
            "status": "mock-status",
        }
    )
