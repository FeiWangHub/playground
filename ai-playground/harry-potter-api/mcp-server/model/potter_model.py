from typing import List, Dict, Optional, Any
from dataclasses import dataclass
from datetime import datetime
import requests
from pydantic import BaseModel
from ..protocol.potter_protocol import ServiceConfig

@dataclass
class Book:
    number: int
    title: str
    original_title: str
    release_date: datetime
    description: str
    pages: int
    cover_url: str
    
    @classmethod
    def from_api_data(cls, data: Dict) -> 'Book':
        return cls(
            number=data['number'],
            title=data['title'],
            original_title=data['originalTitle'],
            release_date=datetime.strptime(data['releaseDate'], '%b %d, %Y'),
            description=data['description'],
            pages=data['pages'],
            cover_url=data['cover']
        )

@dataclass
class Character:
    full_name: str
    nickname: str
    house: str
    actor: str
    children: List[str]
    image_url: str
    birthdate: datetime
    
    @classmethod
    def from_api_data(cls, data: Dict) -> 'Character':
        return cls(
            full_name=data['fullName'],
            nickname=data['nickname'],
            house=data['hogwartsHouse'],
            actor=data['interpretedBy'],
            children=data['children'],
            image_url=data['image'],
            birthdate=datetime.strptime(data['birthdate'], '%b %d, %Y')
        )

@dataclass
class House:
    name: str
    emoji: str
    founder: str
    colors: List[str]
    animal: str
    
    @classmethod
    def from_api_data(cls, data: Dict) -> 'House':
        return cls(
            name=data['house'],
            emoji=data['emoji'],
            founder=data['founder'],
            colors=data['colors'],
            animal=data['animal']
        )

@dataclass
class Spell:
    name: str
    description: str
    
    @classmethod
    def from_api_data(cls, data: Dict) -> 'Spell':
        return cls(
            name=data['spell'],
            description=data['use']
        )

class PotterModel:
    def __init__(self, config: ServiceConfig):
        self.config = config
        self._data: Dict[str, Any] = {}
        self._refresh()
    
    def update_config(self, config: ServiceConfig):
        """更新配置"""
        self.config = config
        self._refresh()
    
    def _refresh(self):
        """刷新数据"""
        self._data = self._fetch_data()
    
    def _fetch_data(self) -> Dict[str, Any]:
        """从 API 获取数据"""
        response = requests.get("https://hp-api.onrender.com/api/characters")
        response.raise_for_status()
        characters = response.json()
        
        response = requests.get("https://hp-api.onrender.com/api/spells")
        response.raise_for_status()
        spells = response.json()
        
        return {
            "characters": characters,
            "spells": spells
        }
    
    def get_characters(self, house: Optional[str] = None, search: Optional[str] = None) -> List[Character]:
        """获取角色列表"""
        characters = self._data.get("characters", [])
        
        if house:
            characters = [c for c in characters if c.get("house") == house]
        
        if search:
            search = search.lower()
            characters = [
                c for c in characters 
                if search in c.get("name", "").lower() or 
                any(search in name.lower() for name in c.get("alternate_names", []))
            ]
        
        return [Character(**c) for c in characters]
    
    def get_houses(self) -> List[House]:
        """获取学院列表"""
        # 从角色数据中提取学院信息
        houses_data = {}
        for char in self._data.get("characters", []):
            house = char.get("house")
            if house and house not in houses_data:
                houses_data[house] = {
                    "id": house.lower(),
                    "name": house,
                    "houseColours": "",  # 需要补充
                    "founder": "",       # 需要补充
                    "animal": "",        # 需要补充
                    "element": "",       # 需要补充
                    "ghost": "",         # 需要补充
                    "commonRoom": "",    # 需要补充
                    "heads": [],         # 需要补充
                    "traits": []         # 需要补充
                }
        
        return [House(**h) for h in houses_data.values()]
    
    def get_spells(self, search: Optional[str] = None) -> List[Spell]:
        """获取咒语列表"""
        spells = self._data.get("spells", [])
        
        if search:
            search = search.lower()
            spells = [
                s for s in spells 
                if search in s.get("name", "").lower() or 
                search in s.get("description", "").lower()
            ]
        
        return [Spell(**s) for s in spells]
    
    def get_books(self, search: Optional[str] = None) -> List[Book]:
        """获取书籍列表"""
        # 这里需要补充实际的书籍数据
        books = [
            {
                "id": "1",
                "title": "Harry Potter and the Philosopher's Stone",
                "series": "Harry Potter",
                "author": "J.K. Rowling",
                "releaseDate": "1997-06-26",
                "pages": 223,
                "cover": "",
                "dedication": "",
                "summary": ""
            }
        ]
        
        if search:
            search = search.lower()
            books = [
                b for b in books 
                if search in b.get("title", "").lower() or 
                search in b.get("summary", "").lower()
            ]
        
        return [Book(**b) for b in books] 