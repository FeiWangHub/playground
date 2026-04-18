#!/bin/bash

# Kill background processes on exit
trap "kill 0" EXIT

echo "🚀 Starting VHS App Copilot..."

# 1. Start Backend
echo "🐍 Starting backend (FastAPI) at http://localhost:8000..."
cd backend
source venv/bin/activate
python3 main.py &
BACKEND_PID=$!
cd ..

# 2. Start Frontend
echo "📦 Starting frontend (Vite) at http://localhost:5173..."
npm run dev &
FRONTEND_PID=$!

echo "✨ Both services are running!"
echo "Press Ctrl+C to stop both."

# Keep the script alive
wait $BACKEND_PID $FRONTEND_PID
