from app.api.router import api_router
from app.core.config import settings
from app.core.exception_handlers import register_exception_handlers
from fastapi import FastAPI

app = FastAPI(title=settings.app_name)
app.include_router(api_router)
register_exception_handlers(app)

if __name__ == "__main__":
    import uvicorn

    uvicorn.run(
        "app.main:app",
        host=settings.host,
        port=settings.port,
        reload=settings.env == "development",
        log_level=settings.log_level,
    )
