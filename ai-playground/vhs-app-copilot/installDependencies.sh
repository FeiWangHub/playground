#!/bin/bash

# Exit on error
set -e

echo "🚀 Starting setup for VHS App Copilot..."

# 1. Install Frontend Dependencies
echo "📦 Installing frontend dependencies (npm)..."
npm install

# 2. Setup Backend Environment
echo "🐍 Setting up Python backend..."
cd backend

if [ ! -d "venv" ]; then
    echo "Creating virtual environment..."
    python3 -m venv venv
fi

echo "Installing backend dependencies..."
source venv/bin/activate
pip install -r requirements.txt
cd ..

echo "✅ Setup complete! You can now start the project using ./start.sh"

echo "⚠️  Don't forget to configure your .env in the backend directory."
