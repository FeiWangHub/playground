from typing import List, Optional
from fastapi import FastAPI, Query
from ..context.potter_context import PotterContext
from ..model.potter_model import Book, Character, House, Spell

class PotterProtocol:
    def __init__(self):
        self.app = FastAPI(
            title="Harry Potter MCP Service",
            description="A Model-Context-Protocol service for Harry Potter data",
            version="1.0.0"
        )
        self.context = PotterContext()
        self._setup_routes()
    
    def _setup_routes(self):
        @self.app.get("/")
        async def root():
            return {
                "message": "Welcome to Harry Potter MCP Service",
                "docs_url": "/docs"
            }
        
        @self.app.get("/api/v1/books", response_model=List[Book])
        async def get_books(search: Optional[str] = Query(None)):
            model = self.context.get_model()
            return model.get_books(search)
        
        @self.app.get("/api/v1/characters", response_model=List[Character])
        async def get_characters(
            house: Optional[str] = Query(None),
            search: Optional[str] = Query(None)
        ):
            model = self.context.get_model()
            return model.get_characters(house, search)
        
        @self.app.get("/api/v1/houses", response_model=List[House])
        async def get_houses():
            model = self.context.get_model()
            return model.get_houses()
        
        @self.app.get("/api/v1/spells", response_model=List[Spell])
        async def get_spells(search: Optional[str] = Query(None)):
            model = self.context.get_model()
            return model.get_spells(search)
        
        @self.app.post("/api/v1/refresh")
        async def refresh_data():
            self.context.refresh()
            return {"message": "Data refreshed successfully"}
        
        @self.app.post("/api/v1/language/{language}")
        async def set_language(language: str):
            self.context.set_language(language)
            return {"message": f"Language set to {language}"}
    
    def run(self, host: str = "0.0.0.0", port: int = 8000):
        """运行服务"""
        import uvicorn
        uvicorn.run(self.app, host=host, port=port)

if __name__ == "__main__":
    protocol = PotterProtocol()
    protocol.run() 