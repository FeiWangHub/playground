from app.core.response import ok
from app.schemas.response import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/build", response_model=ResponseDTO)
def build(name: str = Body(...)):
    return ok(
        {
            "service": "outputEXEBuilderService",
            "job_id": "exe-001",
            "name": name,
            "status": "queued",
        }
    )


@router.get("/jobs", response_model=ResponseDTO)
def jobs():
    return ok(
        {
            "service": "outputEXEBuilderService",
            "jobs": [{"id": "exe-001", "status": "queued"}],
        }
    )


@router.get("/status", response_model=ResponseDTO)
def status(job_id: str):
    return ok(
        {
            "service": "outputEXEBuilderService",
            "job_id": job_id,
            "status": "mock-running",
        }
    )
