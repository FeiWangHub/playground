from app.core.response import ok
from app.schemas.response import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/notebooks", response_model=ResponseDTO)
def create_notebook(title: str = Body(...)):
    return ok({"service": "outputJupyterService", "id": "nb-001", "title": title})


@router.get("/notebooks/{notebook_id}", response_model=ResponseDTO)
def get_notebook(notebook_id: str):
    return ok({"service": "outputJupyterService", "id": notebook_id, "title": "mock"})


@router.get("/kernels", response_model=ResponseDTO)
def kernels():
    return ok({"service": "outputJupyterService", "kernels": ["python3-mock"]})
