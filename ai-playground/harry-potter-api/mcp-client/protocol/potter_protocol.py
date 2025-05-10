from typing import Optional
import tkinter as tk
from tkinter import ttk
from ..context.potter_context import PotterContext
from ..model.potter_model import Book, Character, House, Spell

class PotterProtocol:
    def __init__(self, server_url: str = "http://localhost:8000"):
        self.context = PotterContext(server_url)
        self.root = tk.Tk()
        self.root.title("Harry Potter MCP Client")
        self._setup_ui()
    
    def _setup_ui(self):
        # 创建主框架
        main_frame = ttk.Frame(self.root, padding="10")
        main_frame.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        # 语言选择
        ttk.Label(main_frame, text="Language:").grid(row=0, column=0, sticky=tk.W)
        self.language_var = tk.StringVar(value="en")
        language_combo = ttk.Combobox(main_frame, textvariable=self.language_var)
        language_combo['values'] = ('en', 'es', 'fr', 'it', 'pt', 'uk')
        language_combo.grid(row=0, column=1, sticky=tk.W)
        language_combo.bind('<<ComboboxSelected>>', self._on_language_change)
        
        # 搜索框架
        search_frame = ttk.LabelFrame(main_frame, text="Search", padding="5")
        search_frame.grid(row=1, column=0, columnspan=2, sticky=(tk.W, tk.E))
        
        # 搜索输入
        self.search_var = tk.StringVar()
        search_entry = ttk.Entry(search_frame, textvariable=self.search_var)
        search_entry.grid(row=0, column=0, sticky=(tk.W, tk.E))
        
        # 搜索类型
        self.search_type = tk.StringVar(value="books")
        ttk.Radiobutton(search_frame, text="Books", variable=self.search_type, value="books").grid(row=0, column=1)
        ttk.Radiobutton(search_frame, text="Characters", variable=self.search_type, value="characters").grid(row=0, column=2)
        ttk.Radiobutton(search_frame, text="Spells", variable=self.search_type, value="spells").grid(row=0, column=3)
        
        # 搜索按钮
        ttk.Button(search_frame, text="Search", command=self._on_search).grid(row=0, column=4)
        
        # 结果列表
        self.result_tree = ttk.Treeview(main_frame, columns=("Value",), show="headings")
        self.result_tree.heading("Value", text="Results")
        self.result_tree.grid(row=2, column=0, columnspan=2, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        # 刷新按钮
        ttk.Button(main_frame, text="Refresh Data", command=self._on_refresh).grid(row=3, column=0, columnspan=2)
    
    def _on_language_change(self, event):
        self.context.set_language(self.language_var.get())
        self._update_results()
    
    def _on_search(self):
        query = self.search_var.get()
        search_type = self.search_type.get()
        
        if search_type == "books":
            self.context.search_books(query)
        elif search_type == "characters":
            self.context.search_characters(query)
        elif search_type == "spells":
            self.context.search_spells(query)
        
        self._update_results()
    
    def _on_refresh(self):
        self.context.refresh_data()
        self._update_results()
    
    def _update_results(self):
        # 清空现有结果
        for item in self.result_tree.get_children():
            self.result_tree.delete(item)
        
        # 根据搜索类型显示结果
        search_type = self.search_type.get()
        if search_type == "books":
            for book in self.context.model.books:
                self.result_tree.insert("", "end", values=(f"{book.title} ({book.release_date.year})",))
        elif search_type == "characters":
            for char in self.context.model.characters:
                self.result_tree.insert("", "end", values=(f"{char.full_name} - {char.house}",))
        elif search_type == "spells":
            for spell in self.context.model.spells:
                self.result_tree.insert("", "end", values=(f"{spell.name}: {spell.description}",))
    
    def run(self):
        """运行客户端"""
        self.context.initialize()
        self._update_results()
        self.root.mainloop()

if __name__ == "__main__":
    client = PotterProtocol()
    client.run() 