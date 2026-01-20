#!/usr/bin/env python3
import sys
import os
import argparse

MAIN_PY = """from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def read_root():
    return {"Hello": "World"}

@app.get("/items/{item_id}")
def read_item(item_id: int, q: str = None):
    return {"item_id": item_id, "q": q}
"""

REQUIREMENTS_TXT = """fastapi
uvicorn
"""

README_MD = """# {project_name}

This project was generated using the FastAPI Templates Skill.

## Run

```bash
pip install -r requirements.txt
uvicorn main:app --reload
```
"""

def create_project(project_name):
    if os.path.exists(project_name):
        print(f"Error: Directory '{project_name}' already exists.")
        sys.exit(1)
        
    os.makedirs(project_name)
    
    with open(os.path.join(project_name, "main.py"), "w") as f:
        f.write(MAIN_PY)
        
    with open(os.path.join(project_name, "requirements.txt"), "w") as f:
        f.write(REQUIREMENTS_TXT)
        
    with open(os.path.join(project_name, "README.md"), "w") as f:
        f.write(README_MD.format(project_name=project_name))
        
    print(f"FastAPI project '{project_name}' created successfully!")
    print(f"cd {project_name} && pip install -r requirements.txt && uvicorn main:app --reload")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Scaffold FastAPI Project")
    parser.add_argument("name", help="Project name")
    
    args = parser.parse_args()
    create_project(args.name)
