---
name: icon-generation-skill
description: "Generate simple PNG icons with customizable text and background colors."
---

# Icon Generation Skill

This skill allows you to programmatically generate PNG icons, which is useful for creating app icons, placeholders, or simple graphics.

## Capabilities

- Generate square PNG images.
- Customizable background color, text color, and size.
- Auto-detects system Chinese fonts (macOS/Linux/Windows support).

## Usage

Generate a basic icon:

```bash
python3 .shared/icon-generation-skill/scripts/generate_icon.py "AB" "icon.png"
```

Customize colors (blue background, white text):

```bash
python3 .shared/icon-generation-skill/scripts/generate_icon.py "App" "app_icon.png" --bg "#007bff" --fg "#ffffff"
```
