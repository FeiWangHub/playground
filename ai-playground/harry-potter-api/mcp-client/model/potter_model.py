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

@dataclass
class Character:
    full_name: str
    nickname: str
    house: str
    actor: str
    children: List[str]
    image_url: str
    birthdate: datetime

@dataclass
class House:
    name: str
    emoji: str
    founder: str
    colors: List[str]
    animal: str

@dataclass
class Spell:
    name: str
    description: str

class PotterModel:
    def __init__(self):
        self.books: List[Book] = []
        self.characters: List[Character] = []
        self.houses: List[House] = []
        self.spells: List[Spell] = []
    
    def update_books(self, books_data: List[Dict]):
        self.books = [
            Book(
                number=book['number'],
                title=book['title'],
                original_title=book['originalTitle'],
                release_date=datetime.strptime(book['releaseDate'], '%b %d, %Y'),
                description=book['description'],
                pages=book['pages'],
                cover_url=book['cover']
            ) for book in books_data
        ]
    
    def update_characters(self, characters_data: List[Dict]):
        self.characters = [
            Character(
                full_name=char['fullName'],
                nickname=char['nickname'],
                house=char['hogwartsHouse'],
                actor=char['interpretedBy'],
                children=char['children'],
                image_url=char['image'],
                birthdate=datetime.strptime(char['birthdate'], '%b %d, %Y')
            ) for char in characters_data
        ]
    
    def update_houses(self, houses_data: List[Dict]):
        self.houses = [
            House(
                name=house['house'],
                emoji=house['emoji'],
                founder=house['founder'],
                colors=house['colors'],
                animal=house['animal']
            ) for house in houses_data
        ]
    
    def update_spells(self, spells_data: List[Dict]):
        self.spells = [
            Spell(
                name=spell['spell'],
                description=spell['use']
            ) for spell in spells_data
        ] 