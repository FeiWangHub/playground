from app.core.errors import BusinessError, ErrorCodes
from app.core.http_response import error
from fastapi import FastAPI, HTTPException, Request
from fastapi.exceptions import RequestValidationError
from fastapi.responses import JSONResponse


def register_exception_handlers(app: FastAPI):
    @app.exception_handler(BusinessError)
    async def business_error_handler(request: Request, exc: BusinessError):
        dto = error(exc.code, exc.message, exc.data)
        return JSONResponse(status_code=400, content=dto.model_dump())

    @app.exception_handler(HTTPException)
    async def http_exception_handler(request: Request, exc: HTTPException):
        dto = error(ErrorCodes.UNKNOWN, exc.detail if exc.detail else "http_error")
        return JSONResponse(status_code=exc.status_code, content=dto.model_dump())

    @app.exception_handler(RequestValidationError)
    async def validation_exception_handler(
        request: Request, exc: RequestValidationError
    ):
        dto = error(
            ErrorCodes.VALIDATION_ERROR, "validation_error", {"errors": exc.errors()}
        )
        return JSONResponse(status_code=422, content=dto.model_dump())
