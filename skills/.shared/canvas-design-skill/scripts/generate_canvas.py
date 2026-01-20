#!/usr/bin/env python3
import sys
import argparse

TEMPLATE = """
<!DOCTYPE html>
<html>
<head>
    <title>Canvas Design - {title}</title>
    <style>
        body {{ display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; background: #f0f0f0; }}
        canvas {{ background: white; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }}
    </style>
</head>
<body>
    <canvas id="myCanvas" width="800" height="600"></canvas>
    <script>
        const canvas = document.getElementById('myCanvas');
        const ctx = canvas.getContext('2d');
        const width = canvas.width;
        const height = canvas.height;
        
        // Draw function
        {draw_code}
    </script>
</body>
</html>
"""

DRAW_RECT = """
        ctx.fillStyle = '#3498db';
        ctx.fillRect(200, 150, 400, 300);
        ctx.strokeStyle = '#2980b9';
        ctx.lineWidth = 5;
        ctx.strokeRect(200, 150, 400, 300);
"""

DRAW_CIRCLE = """
        ctx.beginPath();
        ctx.arc(400, 300, 150, 0, 2 * Math.PI);
        ctx.fillStyle = '#e74c3c';
        ctx.fill();
        ctx.lineWidth = 5;
        ctx.strokeStyle = '#c0392b';
        ctx.stroke();
"""

DRAW_GRID = """
        const step = 40;
        ctx.strokeStyle = '#ddd';
        ctx.lineWidth = 1;
        
        for (let x = 0; x <= width; x += step) {
            ctx.beginPath();
            ctx.moveTo(x, 0);
            ctx.lineTo(x, height);
            ctx.stroke();
        }
        
        for (let y = 0; y <= height; y += step) {
            ctx.beginPath();
            ctx.moveTo(0, y);
            ctx.lineTo(width, y);
            ctx.stroke();
        }
"""

DRAW_STAR = """
        function drawStar(cx, cy, spikes, outerRadius, innerRadius) {
            let rot = Math.PI / 2 * 3;
            let x = cx;
            let y = cy;
            let step = Math.PI / spikes;

            ctx.beginPath();
            ctx.moveTo(cx, cy - outerRadius)
            for (i = 0; i < spikes; i++) {
                x = cx + Math.cos(rot) * outerRadius;
                y = cy + Math.sin(rot) * outerRadius;
                ctx.lineTo(x, y)
                rot += step

                x = cx + Math.cos(rot) * innerRadius;
                y = cy + Math.sin(rot) * innerRadius;
                ctx.lineTo(x, y)
                rot += step
            }
            ctx.lineTo(cx, cy - outerRadius)
            ctx.closePath();
            ctx.lineWidth = 5;
            ctx.strokeStyle = '#f1c40f';
            ctx.stroke();
            ctx.fillStyle = '#f39c12';
            ctx.fill();
        }
        
        drawStar(400, 300, 5, 150, 75);
"""

def generate_html(type, output_file):
    code_map = {
        "rect": DRAW_RECT,
        "circle": DRAW_CIRCLE,
        "grid": DRAW_GRID,
        "star": DRAW_STAR
    }
    
    draw_code = code_map.get(type)
    if not draw_code:
        print(f"Error: Unknown type '{type}'. Available types: {', '.join(code_map.keys())}")
        return

    html_content = TEMPLATE.format(title=type.title(), draw_code=draw_code)
    
    with open(output_file, 'w') as f:
        f.write(html_content)
    
    print(f"Canvas HTML generated at: {output_file}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate HTML5 Canvas designs")
    parser.add_argument("type", choices=["rect", "circle", "grid", "star"], help="Type of design to generate")
    parser.add_argument("output", help="Output HTML file path")
    
    args = parser.parse_args()
    generate_html(args.type, args.output)
