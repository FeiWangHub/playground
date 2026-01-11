from typing import Any, Optional
from app.schemas.http_resp_dto import ResponseDTO

def ok(data: Optional[Any] = None, message: str = "success") -> ResponseDTO:
    return ResponseDTO(code=0, message=message, data=data)

def error(code: int, message: str, data: Optional[Any] = None) -> ResponseDTO:
    return ResponseDTO(code=code, message=message, data=data)
