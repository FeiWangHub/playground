#!/usr/bin/env python3
import sys
import argparse
import yt_dlp

def download_video(url, output_path=None, format='best'):
    ydl_opts = {
        'format': format,
        'outtmpl': '%(title)s.%(ext)s'
    }
    
    if output_path:
        ydl_opts['outtmpl'] = f"{output_path}/%(title)s.%(ext)s"
        
    try:
        with yt_dlp.YoutubeDL(ydl_opts) as ydl:
            print(f"Downloading: {url}")
            ydl.download([url])
        print("Download complete!")
    except Exception as e:
        print(f"Error downloading video: {str(e)}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Download videos using yt-dlp")
    parser.add_argument("url", help="Video URL")
    parser.add_argument("--output", "-o", help="Output directory")
    parser.add_argument("--format", "-f", default="best", help="Video format (default: best)")
    
    args = parser.parse_args()
    download_video(args.url, args.output, args.format)
