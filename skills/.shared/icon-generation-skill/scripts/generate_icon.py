#!/usr/bin/env python3
import sys
import argparse
import os
from PIL import Image, ImageDraw, ImageFont

def get_chinese_font():
    # List of common Chinese fonts on macOS/Linux/Windows
    possible_fonts = [
        "/System/Library/Fonts/STHeiti Medium.ttc",  # macOS
        "/System/Library/Fonts/PingFang.ttc",        # macOS
        "/System/Library/Fonts/Hiragino Sans GB.ttc", # macOS
        "/usr/share/fonts/opentype/noto/NotoSansCJK-Regular.ttc", # Linux
        "C:\\Windows\\Fonts\\msyh.ttc",              # Windows
        "Arial Unicode.ttf"                          # Generic
    ]
    
    for font_path in possible_fonts:
        if os.path.exists(font_path):
            return font_path
            
    return None

def generate_icon(text, output_path, size=512, bg_color="#007bff", text_color="#ffffff"):
    # Create image
    img = Image.new('RGBA', (size, size), color=bg_color)
    draw = ImageDraw.Draw(img)
    
    # Load font
    font_path = get_chinese_font()
    if not font_path:
        print("Warning: Chinese font not found, using default. Text may not render correctly.")
        font = ImageFont.load_default()
    else:
        # Calculate font size (approx 50% of image size)
        font_size = int(size * 0.4)
        try:
            font = ImageFont.truetype(font_path, font_size)
        except OSError:
            print(f"Error loading font {font_path}, falling back to default.")
            font = ImageFont.load_default()

    # Calculate text position to center it
    # getbbox returns (left, top, right, bottom)
    bbox = draw.textbbox((0, 0), text, font=font)
    text_width = bbox[2] - bbox[0]
    text_height = bbox[3] - bbox[1]
    
    x = (size - text_width) / 2
    y = (size - text_height) / 2 - (bbox[1] * 0.5) # Adjust for baseline
    
    # Draw text
    draw.text((x, y), text, font=font, fill=text_color)
    
    # Save
    img.save(output_path)
    print(f"Icon generated at: {output_path}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate PNG Icon")
    parser.add_argument("text", help="Text to display on icon")
    parser.add_argument("output", help="Output file path")
    parser.add_argument("--size", type=int, default=512, help="Icon size (default: 512)")
    parser.add_argument("--bg", default="#007bff", help="Background hex color")
    parser.add_argument("--fg", default="#ffffff", help="Text hex color")
    
    args = parser.parse_args()
    generate_icon(args.text, args.output, args.size, args.bg, args.fg)
