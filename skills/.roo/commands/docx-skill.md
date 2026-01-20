# docx-skill

Read and write Microsoft Word documents.

## Prerequisites

Install `python-docx`:

```bash
pip install python-docx
```

## How to Use

### Read Text

```bash
python3 .shared/docx-skill/scripts/docx_tool.py read "path/to/document.docx"
```

### Create Document

Create a text file with basic Markdown (headers `#`, lists `-`):

```bash
python3 .shared/docx-skill/scripts/docx_tool.py create "input.md" "output.docx"
```
