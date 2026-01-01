import subprocess
import sys
import time

import uvicorn
from fastapi import FastAPI

app = FastAPI()


@app.get("/")
def read_root():
    return {"message": "Hello from FastAPI!", "streamlit_url": "http://localhost:8501"}


def launch_streamlit():
    """
    Launch the Streamlit app hub as a background process.
    """
    print("🚀 Launching Streamlit App Hub...")
    # We use subprocess to run the streamlit command
    # This allows FastAPI and Streamlit to run simultaneously
    process = subprocess.Popen(
        [
            sys.executable,
            "-m",
            "streamlit",
            "run",
            "streamlit_mini_apps_hub_home.py",
            "--server.port",
            "8501",
            "--server.headless",
            "true",
        ]
    )
    return process


if __name__ == "__main__":
    # 1. Start Streamlit in the background
    streamlit_process = launch_streamlit()

    # 2. Start FastAPI (Uvicorn) in the foreground
    try:
        print("Starting FastAPI on http://localhost:8000")
        uvicorn.run(app, host="0.0.0.0", port=8000)
    finally:
        # Cleanup: Kill Streamlit when FastAPI stops
        print("Stopping Streamlit...")
        streamlit_process.terminate()
