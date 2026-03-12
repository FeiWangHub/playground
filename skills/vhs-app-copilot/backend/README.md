# VHS App Copilot Backend

This is the FastAPI backend for the VHS App Copilot, providing AI Agent capabilities via OpenRouter.

## Setup

1. **Install Python Dependencies**:
   ```bash
   cd backend
   pip install -r requirements.txt
   ```

2. **Configure Environment Variables**:
   - Copy `.env.example` to `.env`.
   - Add your `OPENROUTER_API_KEY`.

3. **Run the Backend**:
   ```bash
   python main.py
   ```
   The API will be available at `http://localhost:8000`.

## API Endpoints

- `GET /`: Health check.
- `POST /api/chat`: Send messages to the AI Agent.
  - Body: `{ "messages": [...], "model": "..." }`
