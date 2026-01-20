#!/usr/bin/env python3
import sys
import os
import argparse

def create_file(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as f:
        f.write(content)
    print(f"Created: {path}")

def create_skill(name, description):
    # Base paths (assuming running from repo root)
    # Corrected paths based on project structure:
    # skills/.trae/skills/<name>/SKILL.md
    # skills/.shared/<name>/scripts/
    # skills/.roo/commands/<name>.md
    
    # We need to find the 'skills' directory relative to where this script is run
    # Assuming script is run from project root, 'skills' is in current dir
    if os.path.isdir('skills'):
        base_dir = 'skills'
    elif os.path.basename(os.getcwd()) == 'skills':
        base_dir = '.'
    else:
        # Try to locate skills dir
        current = os.getcwd()
        if 'skills' in os.listdir(current):
            base_dir = 'skills'
        else:
            print("Error: Could not locate 'skills' directory. Please run from project root.")
            sys.exit(1)

    trae_dir = os.path.join(base_dir, '.trae', 'skills', name)
    shared_dir = os.path.join(base_dir, '.shared', name)
    roo_dir = os.path.join(base_dir, '.roo', 'commands')
    
    # 1. Create SKILL.md
    skill_md_content = f"""---
name: {name}
description: "{description}"
---

# {name.replace('-', ' ').title()}

{description}

## Capabilities

- [List capabilities here]

## Usage

```bash
# Example command
python3 .shared/{name}/scripts/example.py
```
"""
    create_file(os.path.join(trae_dir, 'SKILL.md'), skill_md_content)

    # 2. Create Roo command doc
    roo_md_content = f"""# {name}

{description}

## Prerequisites

[List prerequisites here]

## How to Use

Run the following command:

```bash
python3 .shared/{name}/scripts/example.py
```
"""
    create_file(os.path.join(roo_dir, f"{name}.md"), roo_md_content)

    # 3. Create shared scripts dir and example script
    script_content = f"""#!/usr/bin/env python3
import sys

def main():
    print("Hello from {name}!")

if __name__ == "__main__":
    main()
"""
    create_file(os.path.join(shared_dir, 'scripts', 'example.py'), script_content)
    
    # Make script executable
    try:
        os.chmod(os.path.join(shared_dir, 'scripts', 'example.py'), 0o755)
    except Exception as e:
        print(f"Warning: Could not make script executable: {e}")

    print(f"\n✅ Skill '{name}' created successfully!")
    print(f"1. Definition: {os.path.join(trae_dir, 'SKILL.md')}")
    print(f"2. Command:    {os.path.join(roo_dir, f'{name}.md')}")
    print(f"3. Scripts:    {os.path.join(shared_dir, 'scripts/')}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Scaffold a new skill")
    parser.add_argument("name", help="Name of the skill (kebab-case)")
    parser.add_argument("description", help="Short description of the skill")
    
    args = parser.parse_args()
    create_skill(args.name, args.description)
