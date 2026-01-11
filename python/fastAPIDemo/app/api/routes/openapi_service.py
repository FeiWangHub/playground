from app.core.http_response import ok
from app.schemas.http_resp_dto import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.get("/spec", response_model=ResponseDTO)
def spec():
    return ok(
        {
            "service": "openAPIService",
            "version": "0.0.1",
            "endpoints": ["spec", "invoke", "providers"],
        }
    )


@router.get("/providers", response_model=ResponseDTO)
def providers():
    return ok({"service": "openAPIService", "providers": ["mock-provider"]})


@router.post("/invoke", response_model=ResponseDTO)
def invoke(operation_id: str = Body(...), params: dict = Body({})):
    return ok(
        {
            "service": "openAPIService",
            "operation": operation_id,
            "result": {"echo": params},
        }
    )
