import pathlib
import runpy

import streamlit as st

st.set_page_config(page_title="Streamlit App Hub", layout="wide")

# Sidebar - Configuration
st.sidebar.title("Mini-APPs Hub")
st.sidebar.subheader("Apps Copilot")

# Hardcoded source directory (hidden from users)
# Pointing to the miniApps subdirectory
dir_path = str(pathlib.Path(__file__).parent / "miniApps")


def list_scripts(d):
    p = pathlib.Path(d)
    files = []
    if p.exists() and p.is_dir():
        for f in p.glob("*.py"):
            if f.name in {"streamlit_mini_apps_hub_home.py"}:
                continue
            files.append(f)
    files.sort(key=lambda x: x.name)
    return files


scripts = list_scripts(dir_path)

# Sidebar - App List
st.sidebar.divider()
# st.sidebar.subheader("Available Apps")

if not scripts:
    st.sidebar.info("No scripts found in directory.")
    selected_script_path = None
else:
    # Use a dictionary to map names to paths for easy access
    script_map = {f.name: str(f) for f in scripts}
    # Radio button in sidebar for selection
    selected_script_name = st.sidebar.radio(
        "Select an app to run:",
        options=list(script_map.keys()),
        index=0,  # Default to the first one
    )
    selected_script_path = script_map[selected_script_name]

st.sidebar.divider()

# Main Area
if selected_script_path:
    # TODO following 3 lines can be deleted
    # st.title(f"Running: {pathlib.Path(selected_script_path).name}")
    # st.caption(f"Path: {selected_script_path}")
    # st.divider()

    try:
        with st.spinner(f"Loading {pathlib.Path(selected_script_path).name}..."):
            # run_path executes the script in the current context
            runpy.run_path(selected_script_path, run_name="__main__")
    except Exception as e:
        st.error(f"Error running script: {e}")
else:
    st.title("Welcome to Streamlit App Hub")
    st.info("Please select an app from the sidebar to begin.")
