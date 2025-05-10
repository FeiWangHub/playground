from typing import List, Dict, Optional
from dataclasses import dataclass
from datetime import datetime

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
    def __init__(self, api_data: Dict):
        self.books = [Book.from_api_data(book) for book in api_data.get('books', [])]
        self.characters = [Character.from_api_data(char) for char in api_data.get('characters', [])]
        self.houses = [House.from_api_data(house) for house in api_data.get('houses', [])]
        self.spells = [Spell.from_api_data(spell) for spell in api_data.get('spells', [])]
    
    def get_books(self, search: Optional[str] = None) -> List[Book]:
        if search:
            return [book for book in self.books if search.lower() in book.title.lower()]
        return self.books
    
    def get_characters(self, house: Optional[str] = None, search: Optional[str] = None) -> List[Character]:
        characters = self.characters
        if house:
            characters = [char for char in characters if char.house == house]
        if search:
            characters = [char for char in characters if search.lower() in char.full_name.lower()]
        return characters
    
    def get_houses(self) -> List[House]:
        return self.houses
    
    def get_spells(self, search: Optional[str] = None) -> List[Spell]:
        if search:
            return [spell for spell in self.spells if search.lower() in spell.name.lower()]
        return self.spells 