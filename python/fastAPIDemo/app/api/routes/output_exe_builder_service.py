from app.core.http_response import ok
from app.schemas.dtos import JobDTO
from app.schemas.http_resp_dto import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/build", response_model=ResponseDTO)
def build(name: str = Body(...)):
    return ok(JobDTO(id="exe-001", name=name, status="queued"))


@router.get("/jobs", response_model=ResponseDTO)
def jobs():
    return ok({"jobs": [JobDTO(id="exe-001", status="queued")]})


@router.get("/status", response_model=ResponseDTO)
def status(job_id: str):
    return ok(JobDTO(id=job_id, status="mock-running"))
