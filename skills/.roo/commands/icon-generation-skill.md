# icon-generation-skill

Generate PNG icons with text.

## Prerequisites

Install `Pillow`:

```bash
pip install Pillow
```

## How to Use

Run the generation script:

```bash
python3 .shared/icon-generation-skill/scripts/generate_icon.py "TEXT" "output.png" [options]
```

**Options:**
- `--size`: Image size (default: 512)
- `--bg`: Background hex color (default: #007bff)
- `--fg`: Text hex color (default: #ffffff)
