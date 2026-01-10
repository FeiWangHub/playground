import time
from datetime import datetime, timedelta

import altair as alt
import numpy as np
import pandas as pd
import streamlit as st

# Page config
st.set_page_config(page_title="Year Jira Tickets Checker", layout="wide")

st.title("Yearly Jira Tickets Checker")

# Main Page - Form
with st.container():
    st.subheader("🔍 Search & Authentication")

    # Organize inputs horizontally for a cleaner toolbar look
    # Using vertical_alignment="bottom" to align the button with input boxes
    c1, c2, c3, c4, c5 = st.columns([1, 1, 1, 1, 1], vertical_alignment="bottom")

    with c1:
        psid = st.text_input("PSID", placeholder="ID")
    with c2:
        password = st.text_input("Password", type="password", placeholder="Pwd")
    with c3:
        year = st.selectbox("Year", options=[2023, 2024, 2025, 2026], index=2)
    with c4:
        board_name = st.text_input("Board", placeholder="e.g. CORE-TECH")
    with c5:
        list_button = st.button(
            "List Tickets", type="primary", use_container_width=True
        )

    st.divider()


def get_mock_jira_data(psid, year, board_name):
    """Simulates a JIRA API call with mock data."""
    # Simulate network latency
    time.sleep(1.5)

    statuses = ["To Do", "In Progress", "Done", "Review", "Blocked"]
    titles = [
        "Fix authentication bug",
        "Refactor database layer",
        "Implement new UI dashboard",
        "API integration with Hub",
        "Optimize performance",
        "Documentation update",
        "Add unit tests for core",
        "Fix CSS layout issues",
        "Update dependencies",
        "Security audit fixes",
        "New feature: Export PDF",
        "Bug: Memory leak in worker",
        "Enhance logging system",
        "CI/CD pipeline optimization",
        "User profile redesign",
        "Support multi-language",
        "Database migration script",
        "Fix edge case in parser",
        "Refactor legacy code",
        "Add telemetry metrics",
    ]

    mock_data = []
    start_date = datetime(year, 1, 1)

    for i in range(20):
        # Generate random dates within the selected year
        created_days = np.random.randint(0, 365)
        created_time = start_date + timedelta(days=created_days)

        status = np.random.choice(statuses)
        completion_time = None
        if status == "Done":
            # Done tickets have a completion time 2-15 days after creation
            # Ensure completion date stays within the same year for demo consistency
            days_to_end_of_year = (datetime(year, 12, 31) - created_time).days
            added_days = int(
                np.random.randint(1, min(15, max(2, days_to_end_of_year + 1)))
            )
            completion_time = created_time + timedelta(days=added_days)

        mock_data.append(
            {
                "Ticket ID": f"{board_name}-{1000 + i}",
                "Title": titles[i],
                "Status": status,
                "Created Time": created_time.strftime("%Y-%m-%d"),
                "Completion Time": (
                    completion_time.strftime("%Y-%m-%d") if completion_time else "N/A"
                ),
                "Completion Month": (
                    completion_time.strftime("%b") if completion_time else None
                ),
            }
        )

    return pd.DataFrame(mock_data)


if list_button:
    if not psid or not password or not board_name:
        st.error("Please provide PSID, Password, and Jira Board Name.")
    else:
        with st.spinner(f"Fetching tickets from {board_name} for {year}..."):
            df = get_mock_jira_data(psid, year, board_name)

            # 1. Summary Metrics
            st.header(f"📊 Summary for {board_name} ({year})")
            m1, m2, m3, m4 = st.columns(4)
            m1.metric("Total Tickets", len(df))
            m2.metric("Done", len(df[df["Status"] == "Done"]))
            m3.metric("In Progress", len(df[df["Status"] == "In Progress"]))
            m4.metric("Others", len(df[~df["Status"].isin(["Done", "In Progress"])]))

            # 2. Visualization - Monthly Completion Trend
            st.subheader("📈 Monthly Ticket Completion Trend")

            # Sort months correctly
            month_order = [
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec",
            ]

            # Filter only completed tickets for the trend
            completed_df = df[df["Status"] == "Done"]

            if len(completed_df) > 0:
                # Prepare categorical data to ensure correct month order (Jan -> Dec)
                month_counts = completed_df["Completion Month"].value_counts()

                # Create a full year template to ensure all months are present
                trend_df = pd.DataFrame(
                    {
                        "Month": month_order,
                        "Tickets Completed": [
                            month_counts.get(m, 0) for m in month_order
                        ],
                    }
                )

                # Use Altair to ensure strict month ordering
                chart = (
                    alt.Chart(trend_df)
                    .mark_bar(color="#29b5e8")
                    .encode(
                        x=alt.X("Month", sort=month_order, title="Month"),
                        y=alt.Y("Tickets Completed", title="Count"),
                        tooltip=["Month", "Tickets Completed"],
                    )
                )
                st.altair_chart(chart, use_container_width=True)
            else:
                st.info("No completed tickets found to show a trend.")

            # 3. Detailed Data Table
            st.subheader("📄 Ticket Details")
            st.dataframe(
                df.drop(columns=["Completion Month"]), use_container_width=True
            )

            st.success(f"Successfully retrieved {len(df)} tickets for user {psid}.")
else:
    # Simple placeholder UI
    col1, col2 = st.columns(2)
    with col1:
        st.markdown(
            """
        ### Features:
        - Secure Authentication (Simulated)
        - Dynamic JIRA Board Filtering
        - Visual Analytics & Trends
        - Exportable Data View
        """
        )
    with col2:
        st.image(
            "https://images.unsplash.com/photo-1517245386807-bb43f82c33c4?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
            caption="Jira Insights Dashboard",
        )
