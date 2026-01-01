import io
import time
import wave

import numpy as np
import pandas as pd
import streamlit as st

st.set_page_config(page_title="Streamlit Feature Demo", layout="wide")

st.title("Streamlit Feature Demo")
st.write("Use the sidebar to explore common features.")

section = st.sidebar.radio(
    "Select module", ["Widgets", "Charts", "Layout", "Data & State", "Media"]
)

if section == "Widgets":
    st.header("Interactive widgets")
    name = st.text_input("Your name")
    age = st.number_input("Age", min_value=0, max_value=120, value=25)
    level = st.slider("Satisfaction", 0, 100, 50)
    color = st.selectbox("Favorite color", ["Red", "Green", "Blue", "Purple"])
    agree = st.checkbox("I agree to the terms")
    date = st.date_input("Pick a date")
    if st.button("Submit"):
        st.success(
            f"Hello {name}, age {age}, satisfaction {level}, color {color}, agree {agree}, date {date}"
        )

    st.subheader("File upload")
    file = st.file_uploader("Upload a text file", type=["txt", "csv"])
    if file is not None:
        content = file.read()
        try:
            text = content.decode("utf-8")
        except Exception:
            text = str(content)
        st.text_area("File content", text, height=200)

elif section == "Charts":
    st.header("Visualization")
    data = pd.DataFrame(np.random.randn(50, 3), columns=list("ABC"))
    st.subheader("Line chart")
    st.line_chart(data)
    st.subheader("Area chart")
    st.area_chart(data)
    st.subheader("Bar chart")
    st.bar_chart(data)

    st.subheader("Map")
    map_data = pd.DataFrame(
        {
            "lat": np.random.randn(100) / 50 + 37.76,
            "lon": np.random.randn(100) / 50 - 122.4,
        }
    )
    st.map(map_data)

elif section == "Layout":
    st.header("Layout & containers")
    col1, col2, col3 = st.columns(3)
    with col1:
        st.metric("Temperature", "30°C", "+2°C")
    with col2:
        st.metric("Speed", "120 km/h", "-5 km/h")
    with col3:
        st.metric("Humidity", "60%", "+10%")

    st.subheader("Progress")
    progress = st.progress(0)
    for percent in range(0, 101, 10):
        time.sleep(0.05)
        progress.progress(percent)

    st.subheader("Tabs")
    tab1, tab2 = st.tabs(["Data", "Info"])
    with tab1:
        st.dataframe(
            pd.DataFrame({"x": np.arange(10), "y": np.random.randint(0, 100, 10)})
        )
    with tab2:
        st.write("This is a tabs example.")

    st.subheader("Expander")
    with st.expander("More content"):
        st.write("Hidden content.")

elif section == "Data & State":
    st.header("Data & state")

    if "counter" not in st.session_state:
        st.session_state.counter = 0
    if st.button("Increment"):
        st.session_state.counter += 1
    st.write(f"Current count: {st.session_state.counter}")

    st.subheader("Form")
    with st.form("feedback_form"):
        email = st.text_input("Email")
        feedback = st.text_area("Feedback")
        submitted = st.form_submit_button("Send")
        if submitted:
            st.success("Thanks for your feedback")

    st.subheader("Cached computation")

    @st.cache_data(ttl=60)
    def expensive_computation(n):
        time.sleep(1)
        return {"sum": sum(range(n)), "n": n}

    n = st.number_input("Compute up to n", min_value=1, value=1000, step=100)
    with st.spinner("Computing"):
        result = expensive_computation(int(n))
    st.json(result)

elif section == "Media":
    st.header("Media")
    st.subheader("Image")
    img = np.random.randint(0, 255, (128, 128, 3), dtype=np.uint8)
    st.image(img, caption="Random image", use_column_width=True)

    st.subheader("Audio")

    def generate_sine(duration_sec=1.0, freq=440, sample_rate=44100):
        t = np.linspace(0, duration_sec, int(sample_rate * duration_sec), False)
        tone = (np.sin(2 * np.pi * freq * t) * 32767).astype(np.int16)
        buffer = io.BytesIO()
        with wave.open(buffer, "wb") as wf:
            wf.setnchannels(1)
            wf.setsampwidth(2)
            wf.setframerate(sample_rate)
            wf.writeframes(tone.tobytes())
        buffer.seek(0)
        return buffer

    audio_buffer = generate_sine()
    st.audio(audio_buffer)
