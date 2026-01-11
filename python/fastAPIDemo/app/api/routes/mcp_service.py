from app.core.response import ok
from app.schemas.response import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.get("/capabilities", response_model=ResponseDTO)
def capabilities():
    return ok({"service": "MCPService", "capabilities": ["mock-capability"]})


@router.get("/status", response_model=ResponseDTO)
def status():
    return ok({"service": "MCPService", "status": "ok"})


@router.post("/command", response_model=ResponseDTO)
def command(name: str = Body(...), args: dict = Body({})):
    return ok({"service": "MCPService", "command": name, "result": {"echo": args}})
