from app.core.http_response import ok
from app.schemas.http_resp_dto import ResponseDTO
from fastapi import APIRouter, Body

router = APIRouter()


@router.post("/login", response_model=ResponseDTO)
def login(username: str = Body(...), password: str = Body(...)):
    return ok({"service": "loginService", "user": username, "token": "mock-token"})


@router.post("/logout", response_model=ResponseDTO)
def logout(token: str = Body(...)):
    return ok({"service": "loginService", "logged_out": True, "token": token})


@router.get("/me", response_model=ResponseDTO)
def me():
    return ok({"service": "loginService", "user": "mock-user"})
