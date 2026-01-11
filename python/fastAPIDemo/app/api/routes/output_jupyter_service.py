from app.core.http_response import ok
from app.schemas.dtos import KernelListDTO, NotebookDTO
from app.schemas.http_resp_dto import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/notebooks", response_model=ResponseDTO)
def create_notebook(title: str = Body(...)):
    return ok(NotebookDTO(id="nb-001", title=title))


@router.get("/notebooks/{notebook_id}", response_model=ResponseDTO)
def get_notebook(notebook_id: str):
    return ok(NotebookDTO(id=notebook_id, title="mock"))


@router.get("/kernels", response_model=ResponseDTO)
def kernels():
    return ok(KernelListDTO(kernels=["python3-mock"]))
