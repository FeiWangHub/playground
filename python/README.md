# Streamlit Demo Run Guide

This document describes how to run `python/streamlit_demo.py`.

## Environment Setup

- Use a virtual environment to avoid system restrictions (PEP 668).
- From the repository root:

```bash
python3 -m venv .venv
source .venv/bin/activate
```

## Install Dependencies

- With the virtual environment activated:

```bash
pip install -r python/requirements.txt
```

## Run the Demo

- Start the Streamlit app:

```bash
streamlit run python/streamlit_demo.py
```

- If the `streamlit` command is unavailable, use the module form:

```bash
python -m streamlit run python/streamlit_demo.py
```

- After startup, a local preview URL will open (typically `http://localhost:8501`).

## Troubleshooting

- If you see an "externally-managed-environment / PEP 668" error, you are not using a virtual environment. Create and activate `.venv` as shown above.
- Exit the virtual environment:

```bash
deactivate
```
