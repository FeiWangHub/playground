import streamlit as st
import numpy as np
import pandas as pd

st.set_page_config(page_title="Random Numbers Chart", layout="centered")

st.title("Random Number Generator & Analysis")
st.write("Generate 10 random numbers and visualize them with an average line.")

if st.button("Generate 10 random numbers"):
    # Generate 10 random numbers less than 100
    random_data = np.random.randint(0, 100, size=10)
    avg_value = np.mean(random_data)
    
    # Create a DataFrame for visualization
    # We include 'Value' and a constant 'Average' column
    df = pd.DataFrame({
        'Index': range(1, 11),
        'Value': random_data,
        'Average': [avg_value] * 10
    }).set_index('Index')
    
    # Display the numbers
    st.subheader("Generated Numbers")
    cols = st.columns(10)
    for i, val in enumerate(random_data):
        cols[i].metric(label=f"#{i+1}", value=val)
    
    st.write(f"**Average Value:** {avg_value:.2f}")
    
    # Draw the chart
    st.subheader("Visualization")
    # Using line_chart which can display multiple columns as different lines
    st.line_chart(df)
    
    # Additional info
    st.info(f"The numbers were generated using NumPy and visualized with Pandas and Streamlit's built-in line chart.")
else:
    st.info("Click the button above to start.")
