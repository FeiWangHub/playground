from typing import Dict, Optional
import requests
from ..model.potter_model import PotterModel

class PotterContext:
    BASE_URL = "https://potterapi-fedeperin.vercel.app"
    
    def __init__(self, language: str = "en"):
        self.language = language
        self._model = None
        self._api_data = None
    
    def _fetch_api_data(self) -> Dict:
        """从 API 获取所有数据"""
        endpoints = ['books', 'characters', 'houses', 'spells']
        data = {}
        
        for endpoint in endpoints:
            url = f"{self.BASE_URL}/{self.language}/{endpoint}"
            response = requests.get(url)
            response.raise_for_status()
            data[endpoint] = response.json()
        
        return data
    
    def initialize(self) -> None:
        """初始化上下文，加载所有数据"""
        if not self._api_data:
            self._api_data = self._fetch_api_data()
            self._model = PotterModel(self._api_data)
    
    def get_model(self) -> PotterModel:
        """获取模型实例"""
        if not self._model:
            self.initialize()
        return self._model
    
    def refresh(self) -> None:
        """刷新数据"""
        self._api_data = self._fetch_api_data()
        self._model = PotterModel(self._api_data)
    
    def set_language(self, language: str) -> None:
        """设置语言"""
        if language != self.language:
            self.language = language
            self.refresh() 