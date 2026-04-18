import os
from typing import List, Optional

from dotenv import load_dotenv
from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from openai import OpenAI
from pydantic import BaseModel

# Load environment variables
load_dotenv()

app = FastAPI(title="VHS App Copilot Backend")

# Configure CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # Adjust in production
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# OpenRouter client configuration
client = OpenAI(
    base_url="https://openrouter.ai/api/v1",
    api_key=os.getenv("OPENROUTER_API_KEY"),
)


class Message(BaseModel):
    role: str
    content: str


class ChatRequest(BaseModel):
    messages: List[Message]
    model: Optional[str] = "stepfun/step-3.5-flash:free"


class ChatResponse(BaseModel):
    id: str
    role: str
    content: str
    type: str = "text"


@app.get("/")
async def root():
    return {"status": "ok", "message": "VHS App Copilot API is running"}


@app.post("/api/chat", response_model=ChatResponse)
async def chat(request: ChatRequest):
    if not os.getenv("OPENROUTER_API_KEY"):
        raise HTTPException(status_code=500, detail="OPENROUTER_API_KEY not configured")

    try:
        completion = client.chat.completions.create(
            extra_headers={
                "HTTP-Referer": os.getenv("SITE_URL", "http://localhost:3000"),
                "X-Title": os.getenv("SITE_NAME", "VHS App Copilot"),
            },
            model=request.model,
            messages=[{"role": m.role, "content": m.content} for m in request.messages],
        )

        assistant_content = completion.choices[0].message.content

        return ChatResponse(
            id=completion.id,
            role="assistant",
            content=assistant_content,
            type="markdown",
        )
    except Exception as e:
        print(f"Error calling OpenRouter: {e}")
        raise HTTPException(status_code=500, detail=str(e))


if __name__ == "__main__":
    import uvicorn

    uvicorn.run(app, host="0.0.0.0", port=8000)
