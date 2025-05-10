from typing import List, Optional, Dict, Any
from fastapi import FastAPI, Query
from pydantic import BaseModel
from ..context.potter_context import PotterContext
from ..model.potter_model import Book, Character, House, Spell

class ServiceConfig(BaseModel):
    """服务配置模型"""
    host: str = "0.0.0.0"
    port: int = 8000
    api_version: str = "v1"
    base_path: str = "/api"
    language: str = "en"
    cache_ttl: int = 3600  # 缓存时间（秒）

class PotterProtocol:
    def __init__(self, config: Optional[ServiceConfig] = None):
        self.config = config or ServiceConfig()
        self.app = FastAPI(
            title="Harry Potter MCP Service",
            description="""
            A Model-Context-Protocol service for Harry Potter data.
            
            This service provides:
            - Character information
            - House details
            - Spell descriptions
            - Book information
            
            All data is fetched from the Harry Potter API and cached locally.
            """,
            version="1.0.0",
            openapi_tags=[
                {
                    "name": "characters",
                    "description": "Operations with Harry Potter characters"
                },
                {
                    "name": "houses",
                    "description": "Information about Hogwarts houses"
                },
                {
                    "name": "spells",
                    "description": "Magical spells and their effects"
                },
                {
                    "name": "books",
                    "description": "Harry Potter book information"
                }
            ]
        )
        self.context = PotterContext(self.config)
        self._setup_routes()
    
    def _setup_routes(self):
        @self.app.get("/")
        async def root():
            """服务根路径，返回服务信息和文档链接"""
            return {
                "service": "Harry Potter MCP Service",
                "version": self.app.version,
                "docs_url": "/docs",
                "redoc_url": "/redoc",
                "openapi_url": "/openapi.json",
                "config": self.config.dict()
            }
        
        @self.app.get(f"{self.config.base_path}/{self.config.api_version}/characters", 
                     response_model=List[Character],
                     tags=["characters"])
        async def get_characters(
            house: Optional[str] = Query(None, description="Filter by house name"),
            search: Optional[str] = Query(None, description="Search in character names")
        ):
            """获取角色列表，支持按学院和名称搜索"""
            model = self.context.get_model()
            return model.get_characters(house, search)
        
        @self.app.get(f"{self.config.base_path}/{self.config.api_version}/houses", 
                     response_model=List[House],
                     tags=["houses"])
        async def get_houses():
            """获取所有学院信息"""
            model = self.context.get_model()
            return model.get_houses()
        
        @self.app.get(f"{self.config.base_path}/{self.config.api_version}/spells", 
                     response_model=List[Spell],
                     tags=["spells"])
        async def get_spells(
            search: Optional[str] = Query(None, description="Search in spell names")
        ):
            """获取咒语列表，支持名称搜索"""
            model = self.context.get_model()
            return model.get_spells(search)
        
        @self.app.get(f"{self.config.base_path}/{self.config.api_version}/books", 
                     response_model=List[Book],
                     tags=["books"])
        async def get_books(
            search: Optional[str] = Query(None, description="Search in book titles")
        ):
            """获取书籍列表，支持标题搜索"""
            model = self.context.get_model()
            return model.get_books(search)
        
        @self.app.post(f"{self.config.base_path}/{self.config.api_version}/refresh")
        async def refresh_data():
            """刷新缓存数据"""
            self.context.refresh()
            return {"message": "Data refreshed successfully"}
        
        @self.app.post(f"{self.config.base_path}/{self.config.api_version}/config")
        async def update_config(config: ServiceConfig):
            """更新服务配置"""
            self.config = config
            self.context.update_config(config)
            return {"message": "Configuration updated successfully", "config": config.dict()}
    
    def run(self):
        """运行服务"""
        import uvicorn
        uvicorn.run(
            self.app, 
            host=self.config.host, 
            port=self.config.port,
            log_level="info"
        )

if __name__ == "__main__":
    protocol = PotterProtocol()
    protocol.run() 