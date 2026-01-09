import numpy as np
import pandas as pd
import streamlit as st

st.set_page_config(page_title="Random Numbers Chart", layout="centered")

st.title("Random Number Generator & Analysis")
st.write("Generate 10 random numbers and visualize them with an average line.")

# Default data for initial load
DEFAULT_DATA = np.array([10, 25, 45, 30, 60, 85, 70, 90, 50, 20])

# Initialize session state for the data if it doesn't exist
if "chart_data" not in st.session_state:
    st.session_state.chart_data = DEFAULT_DATA

# Button to generate new data
if st.button("Generate 10 random numbers"):
    # Generate 10 random numbers less than 100
    st.session_state.chart_data = np.random.randint(0, 100, size=10)

# Always use the data from session state
random_data = st.session_state.chart_data
avg_value = np.mean(random_data)

# Create a DataFrame for visualization
df = pd.DataFrame(
    {"Index": range(1, 11), "Value": random_data, "Average": [avg_value] * 10}
).set_index("Index")

# Display the numbers
st.subheader("Current Numbers")
cols = st.columns(10)
for i, val in enumerate(random_data):
    cols[i].metric(label=f"#{i+1}", value=val)

st.write(f"**Average Value:** {avg_value:.2f}")

# Draw the chart
st.subheader("Visualization")
# Using line_chart which can display multiple columns as different lines
st.line_chart(df)

# Additional info
st.info(
    f"The numbers are visualized with Pandas and Streamlit's built-in line chart. Click 'Generate' to refresh with random values."
)
