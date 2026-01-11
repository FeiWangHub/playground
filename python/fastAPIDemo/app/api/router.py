from app.api.routes.root import router as root_router
from app.api.routes.ai_service import router as ai_router
from app.api.routes.login_service import router as login_router
from app.api.routes.openapi_service import router as openapi_router
from app.api.routes.mcp_service import router as mcp_router
from app.api.routes.output_exe_builder_service import router as exe_router
from app.api.routes.output_jupyter_service import router as jupyter_router
from app.api.routes.output_streamlit_service import router as streamlit_router
from app.api.routes.output_app_runner_service import router as apprunner_router
from fastapi import APIRouter

api_router = APIRouter()
api_router.include_router(root_router, tags=["root"])
api_router.include_router(ai_router, prefix="/ai", tags=["AIService"])
api_router.include_router(login_router, prefix="/login", tags=["loginService"])
api_router.include_router(openapi_router, prefix="/openapi", tags=["openAPIService"])
api_router.include_router(mcp_router, prefix="/mcp", tags=["MCPService"])
api_router.include_router(exe_router, prefix="/exe", tags=["outputEXEBuilderService"])
api_router.include_router(jupyter_router, prefix="/jupyter", tags=["outputJupyterService"])
api_router.include_router(streamlit_router, prefix="/streamlit", tags=["outputStreamLitService"])
api_router.include_router(apprunner_router, prefix="/apprunner", tags=["outputAppRunnerService"])
