# FastAPI Demo

## 运行
1. 创建虚拟环境
   - macOS/Linux: `python3 -m venv .venv && source .venv/bin/activate`
   - Windows: `python -m venv .venv && .venv\\Scripts\\activate`
2. 安装依赖
   - `pip install -r requirements.txt`
3. 复制环境配置文件
   - `cp .env.example .env`
4. 启动服务
   - 方式一（推荐开发环境）: `uvicorn app.main:app --reload --port 8000`
   - 方式二（使用 Python 启动）: `python -m app.main`
5. 验证接口
   - 健康检查: `http://localhost:8000/health`
   - 应用信息: `http://localhost:8000/info`

## 分类服务示例
- AIService: GET `/ai/status`, GET `/ai/models`, POST `/ai/generate`
- loginService: POST `/login/login`, POST `/login/logout`, GET `/login/me`
- openAPIService: GET `/openapi/spec`, GET `/openapi/providers`, POST `/openapi/invoke`
- MCPService: GET `/mcp/capabilities`, GET `/mcp/status`, POST `/mcp/command`
- outputEXEBuilderService: POST `/exe/build`, GET `/exe/jobs`, GET `/exe/status?job_id=exe-001`
- outputJupyterService: POST `/jupyter/notebooks`, GET `/jupyter/notebooks/{id}`, GET `/jupyter/kernels`
- outputStreamLitService: POST `/streamlit/apps`, GET `/streamlit/apps`, GET `/streamlit/status?app_id=sl-001`
- outputAppRunnerService: POST `/apprunner/deploy`, GET `/apprunner/deployments`, GET `/apprunner/status?deployment_id=ar-001`

## 统一响应结构
所有接口返回如下结构：
```
{
  "code": 0,
  "message": "success",
  "data": {},
  "request_id": "xxxxxxxx",
  "timestamp": 1730000000000
}
```
响应模型位置：`app/schemas/http_resp_dto.py`；响应快捷函数位置：`app/core/http_response.py`

## 异常与错误码
- 统一异常处理：BusinessError、HTTPException、校验异常均包装为上述响应结构
- 错误码示例：
  - 1000 UNKNOWN
  - 1001 INVALID_TOKEN
  - 1002 VALIDATION_ERROR
- 触发业务异常示例：
  - 在路由中 `raise BusinessError(1001, "invalid token")`
