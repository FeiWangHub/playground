from app.api.router import api_router
from app.core.config import settings
from app.core.exception_handlers import register_exception_handlers
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from fastapi.responses import FileResponse
from fastapi.staticfiles import StaticFiles

app = FastAPI(title=settings.app_name, docs_url="/swagger", redoc_url="/redoc")
app.include_router(api_router)
register_exception_handlers(app)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Mount static frontend under /public_web_files
app.mount(
    "/public_web_files",
    StaticFiles(directory="public_web_files", html=True),
    name="public_web_files",
)


# Serve index.html at root
@app.get("/")
def index():
    return FileResponse("public_web_files/index.html")


# SPA fallback: unmatched paths return index.html (after API routes)
@app.get("/{spa_path:path}")
def spa(spa_path: str):
    return FileResponse("public_web_files/index.html")


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(
        "app.main:app",
        host=settings.host,
        port=settings.port,
        reload=settings.env == "development",
        log_level=settings.log_level,
    )
