from typing import Dict, Optional
import requests
from ..model.potter_model import PotterModel

class PotterContext:
    def __init__(self, server_url: str = "http://localhost:8000"):
        self.server_url = server_url
        self.model = PotterModel()
        self.language = "en"
    
    def _make_request(self, endpoint: str, method: str = "GET", params: Optional[Dict] = None) -> Dict:
        """发送请求到服务器"""
        url = f"{self.server_url}{endpoint}"
        response = requests.request(method, url, params=params)
        response.raise_for_status()
        return response.json()
    
    def initialize(self) -> None:
        """初始化数据"""
        # 获取所有数据
        self.refresh_data()
    
    def refresh_data(self) -> None:
        """刷新所有数据"""
        # 获取书籍
        books = self._make_request("/api/v1/books")
        self.model.update_books(books)
        
        # 获取角色
        characters = self._make_request("/api/v1/characters")
        self.model.update_characters(characters)
        
        # 获取学院
        houses = self._make_request("/api/v1/houses")
        self.model.update_houses(houses)
        
        # 获取咒语
        spells = self._make_request("/api/v1/spells")
        self.model.update_spells(spells)
    
    def set_language(self, language: str) -> None:
        """设置语言"""
        if language != self.language:
            self.language = language
            self._make_request(f"/api/v1/language/{language}", method="POST")
            self.refresh_data()
    
    def search_books(self, query: str) -> None:
        """搜索书籍"""
        books = self._make_request("/api/v1/books", params={"search": query})
        self.model.update_books(books)
    
    def search_characters(self, query: str, house: Optional[str] = None) -> None:
        """搜索角色"""
        params = {"search": query}
        if house:
            params["house"] = house
        characters = self._make_request("/api/v1/characters", params=params)
        self.model.update_characters(characters)
    
    def search_spells(self, query: str) -> None:
        """搜索咒语"""
        spells = self._make_request("/api/v1/spells", params={"search": query})
        self.model.update_spells(spells) 