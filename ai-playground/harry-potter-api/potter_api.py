import requests
from typing import Optional, List, Dict, Union

class PotterAPI:
    BASE_URL = "https://potterapi-fedeperin.vercel.app"
    
    def __init__(self, language: str = "en"):
        """
        初始化 PotterAPI 客户端
        :param language: 语言代码 (en, es, fr, it, pt, uk)
        """
        self.language = language
        
    def _make_request(self, endpoint: str, params: Optional[Dict] = None) -> Union[Dict, List]:
        """
        发送 API 请求
        """
        url = f"{self.BASE_URL}/{self.language}/{endpoint}"
        response = requests.get(url, params=params)
        response.raise_for_status()
        return response.json()
    
    def get_books(self, index: Optional[int] = None, max_items: Optional[int] = None, 
                 page: Optional[int] = None, search: Optional[str] = None) -> List[Dict]:
        """
        获取哈利波特书籍信息
        """
        params = {k: v for k, v in {
            'index': index,
            'max': max_items,
            'page': page,
            'search': search
        }.items() if v is not None}
        return self._make_request('books', params)
    
    def get_random_book(self) -> Dict:
        """
        获取随机一本书的信息
        """
        return self._make_request('books/random')
    
    def get_characters(self, index: Optional[int] = None, max_items: Optional[int] = None,
                      page: Optional[int] = None, search: Optional[str] = None) -> List[Dict]:
        """
        获取角色信息
        """
        params = {k: v for k, v in {
            'index': index,
            'max': max_items,
            'page': page,
            'search': search
        }.items() if v is not None}
        return self._make_request('characters', params)
    
    def get_random_character(self) -> Dict:
        """
        获取随机一个角色的信息
        """
        return self._make_request('characters/random')
    
    def get_houses(self) -> List[Dict]:
        """
        获取霍格沃茨学院信息
        """
        return self._make_request('houses')
    
    def get_random_house(self) -> Dict:
        """
        获取随机一个学院的信息
        """
        return self._make_request('houses/random')
    
    def get_spells(self, index: Optional[int] = None, max_items: Optional[int] = None,
                  page: Optional[int] = None, search: Optional[str] = None) -> List[Dict]:
        """
        获取咒语信息
        """
        params = {k: v for k, v in {
            'index': index,
            'max': max_items,
            'page': page,
            'search': search
        }.items() if v is not None}
        return self._make_request('spells', params)
    
    def get_random_spell(self) -> Dict:
        """
        获取随机一个咒语的信息
        """
        return self._make_request('spells/random')

# 使用示例
if __name__ == "__main__":
    # 创建 API 客户端实例
    api = PotterAPI(language="en")
    
    # 获取所有书籍
    books = api.get_books()
    print("Books:", books[:2])  # 只打印前两本书
    
    # 搜索特定角色
    weasley_characters = api.get_characters(search="Weasley")
    print("\nWeasley characters:", weasley_characters)
    
    # 获取所有学院
    houses = api.get_houses()
    print("\nHouses:", houses)
    
    # 获取随机咒语
    random_spell = api.get_random_spell()
    print("\nRandom spell:", random_spell) 