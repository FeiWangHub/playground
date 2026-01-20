---
name: video-downloader-skill
description: "Download videos from YouTube and other platforms using yt-dlp."
---

# Video Downloader Skill

This skill allows you to download videos from various online platforms using the powerful `yt-dlp` library.

## Capabilities

- Download videos from YouTube, Vimeo, Twitter, and hundreds of other sites.
- Support for format selection (best, audio-only, etc.).
- Custom output directory support.

## Usage

Download a video:

```bash
python3 .shared/video-downloader-skill/scripts/download.py "https://www.youtube.com/watch?v=VIDEO_ID"
```

Download to specific folder:

```bash
python3 .shared/video-downloader-skill/scripts/download.py "URL" --output "./downloads"
```
