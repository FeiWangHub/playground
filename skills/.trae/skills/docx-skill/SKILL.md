---
name: docx-skill
description: "Read text from and create Microsoft Word documents (.docx)."
---

# DOCX Skill

This skill provides tools to interact with Microsoft Word documents.

## Capabilities

- **Read**: Extract full text from `.docx` files.
- **Create**: Generate `.docx` files from simple Markdown-formatted text.

## Usage

### Read a Document

```bash
python3 .shared/docx-skill/scripts/docx_tool.py read "document.docx"
```

### Create a Document

```bash
python3 .shared/docx-skill/scripts/docx_tool.py create "content.md" "output.docx"
```
