import requests
import json
from typing import Dict, List, Optional

class HarryPotterClient:
    def __init__(self, base_url: str = "http://localhost:8000"):
        self.base_url = base_url

    def get_all_characters(self) -> List[Dict]:
        """获取所有角色信息"""
        response = requests.get(f"{self.base_url}/characters")
        response.raise_for_status()
        return response.json()

    def get_character(self, index: int) -> Dict:
        """获取特定角色信息"""
        response = requests.get(f"{self.base_url}/characters/{index}")
        response.raise_for_status()
        return response.json()

    def get_all_spells(self) -> List[Dict]:
        """获取所有咒语信息"""
        response = requests.get(f"{self.base_url}/spells")
        response.raise_for_status()
        return response.json()

def main():
    client = HarryPotterClient()
    
    # 测试获取所有角色
    print("获取所有角色：")
    characters = client.get_all_characters()
    print(f"找到 {len(characters)} 个角色")
    
    # 测试获取第一个角色
    if characters:
        first_character = client.get_character(0)
        print("\n第一个角色信息：")
        print(json.dumps(first_character, indent=2, ensure_ascii=False))
    
    # 测试获取所有咒语
    print("\n获取所有咒语：")
    spells = client.get_all_spells()
    print(f"找到 {len(spells)} 个咒语")

if __name__ == "__main__":
    main() 