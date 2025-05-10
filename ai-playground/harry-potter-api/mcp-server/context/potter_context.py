from typing import Dict, Optional, Any
import requests
from ..protocol.potter_protocol import ServiceConfig
from ..model.potter_model import PotterModel

class PotterContext:
    BASE_URL = "https://potterapi-fedeperin.vercel.app"
    
    def __init__(self, config: ServiceConfig):
        self.config = config
        self._model: Optional[PotterModel] = None
        self._cache: Dict[str, Any] = {}
        self._last_refresh = 0
    
    def _fetch_api_data(self) -> Dict:
        """从 API 获取所有数据"""
        endpoints = ['books', 'characters', 'houses', 'spells']
        data = {}
        
        for endpoint in endpoints:
            url = f"{self.BASE_URL}/{self.config.language}/{endpoint}"
            response = requests.get(url)
            response.raise_for_status()
            data[endpoint] = response.json()
        
        return data
    
    def get_model(self) -> PotterModel:
        """获取或创建模型实例"""
        if self._model is None:
            self._model = PotterModel(self.config)
        return self._model
    
    def update_config(self, config: ServiceConfig):
        """更新配置"""
        self.config = config
        if self._model:
            self._model.update_config(config)
        self.refresh()
    
    def refresh(self):
        """刷新缓存数据"""
        self._cache.clear()
        self._last_refresh = 0
        if self._model:
            self._model.refresh()
    
    def get_cache(self, key: str) -> Optional[Any]:
        """从缓存获取数据"""
        if key in self._cache:
            return self._cache[key]
        return None
    
    def set_cache(self, key: str, value: Any):
        """设置缓存数据"""
        self._cache[key] = value
    
    def set_language(self, language: str) -> None:
        """设置语言"""
        if language != self.config.language:
            self.config.language = language
            self.refresh() 