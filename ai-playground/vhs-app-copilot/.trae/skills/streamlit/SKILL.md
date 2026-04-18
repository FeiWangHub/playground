---
name: "streamlit"
description: "Assists with Streamlit web apps, data dashboards, ML/AI UIs, and interactive Python visualizations. Invoke when user wants to build, debug, or optimize Streamlit applications."
---

# Streamlit Skill

Comprehensive assistance with Streamlit development, including API reference, tutorials, deployment guides, and best practices.

## When to Use This Skill

This skill should be triggered when:
- **Building web apps** with Python for data science, ML/AI, or analytics.
- **Creating dashboards** with interactive visualizations and real-time data.
- **Developing data apps** that need rapid prototyping and deployment.
- **Implementing widgets** like buttons, sliders, file uploaders, or chat interfaces.
- **Debugging Streamlit apps** and troubleshooting common issues (e.g., state management, caching).
- **Optimizing performance** with `@st.cache_data`, `@st.cache_resource`, and `@st.fragment`.

## Key Concepts

### Core Architecture
**Script-based execution**: Streamlit apps run as Python scripts that rerun from top to bottom on every user interaction.
**Session State**: Persistent data storage across reruns using `st.session_state`.
**Caching**: Use `@st.cache_data` for data operations and `@st.cache_resource` for expensive resources like ML models or database connections.

## Quick Reference

### Example: Basic Display & Interactive Widgets

```python
import streamlit as st
import pandas as pd

# Basic display
st.title("Streamlit Dashboard")
st.write("Hello, World!")

# Interactive widgets
if 'count' not in st.session_state:
    st.session_state.count = 0

if st.button('Increment'):
    st.session_state.count += 1

st.write(f'Count: {st.session_state.count}')

# Displaying data
df = pd.DataFrame({'A': [1, 2, 3], 'B': [4, 5, 6]})
st.dataframe(df)
```

## Debugging & Troubleshooting

- **Check Session State**: Ensure keys exist before accessing them.
- **Caching Issues**: Clear cache with `st.cache_data.clear()` or `st.cache_resource.clear()` if data is stale.
- **Execution Flow**: Remember that code runs top-to-bottom on every interaction.
- **Secrets Management**: Use `st.secrets` for API keys and database credentials.
