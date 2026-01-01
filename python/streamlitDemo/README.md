# Apps Copilot - Mini-APP Hub

This project provides a centralized hub to manage and run multiple Streamlit mini-applications.

## How to Run

### Option 1: Using `uv` (Recommended)
`uv` is a fast Python package manager.

1.  **Install Dependencies & Run**:
    ```bash
    cd python/streamlitDemo
    uv run streamlit run streamlit_mini_apps_hub_home.py
    ```

### Option 2: Standard Python Virtual Environment
1.  **Create and activate a virtual environment**:
    ```bash
    cd python/streamlitDemo
    python3 -m venv .venv
    source .venv/bin/activate  # macOS/Linux
    ```
2.  **Install dependencies**:
    ```bash
    pip install .
    ```
3.  **Run the App Hub**:
    ```bash
    streamlit run streamlit_mini_apps_hub_home.py
    ```

### Running with FastAPI Launcher
You can also run the FastAPI app which automatically starts the Streamlit hub:
```bash
cd python/streamlitDemo
uv run python fastapi_launcher.py
```

### How it works:
- **Sidebar**: Select any app from the "Available Apps" list. The app list is automatically scanned from the `miniApps/` directory.
- **Main Area**: The selected app will load and run immediately on the right.

## Directory Structure

- `streamlit_mini_apps_hub_home.py`: The main entry point (App Hub).
- `miniApps/`: Folder containing all individual Streamlit scripts.
- `requirements.txt`: Project dependencies (Pandas, Plotly, etc.).

## Troubleshooting

- **Environment Error**: If you see "externally-managed-environment", ensure you are using the virtual environment (`.venv`).
- **Isolation**: Apps run in the same process. Avoid global mutable state across different scripts.
