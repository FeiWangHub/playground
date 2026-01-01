# Apps Copilot - Mini-APP Hub

This project provides a centralized hub to manage and run multiple Streamlit mini-applications.

## Setup

1. **Create and activate a virtual environment**:
   ```bash
   python3 -m venv .venv
   source .venv/bin/activate
   ```

2. **Install dependencies**:
   ```bash
   pip install -r python/streamlitDemo/requirements.txt
   ```

## Usage

Start the main hub to explore all available mini-apps:

```bash
streamlit run python/streamlitDemo/streamlit_mini_apps_hub_home.py
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
