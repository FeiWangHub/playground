from typing import Any, Optional

class BusinessError(Exception):
    def __init__(self, code: int, message: str, data: Optional[Any] = None):
        self.code = code
        self.message = message
        self.data = data

class ErrorCodes:
    UNKNOWN = 1000
    INVALID_TOKEN = 1001
    VALIDATION_ERROR = 1002
