from typing import Any, Optional
from pydantic import BaseModel
import time
import uuid

class ResponseDTO(BaseModel):
    code: int = 0
    message: str = "success"
    data: Optional[Any] = None
    request_id: str = uuid.uuid4().hex
    timestamp: int = int(time.time() * 1000)
