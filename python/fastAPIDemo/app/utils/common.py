import time
import uuid

def now_ms() -> int:
    return int(time.time() * 1000)

def generate_id(prefix: str) -> str:
    return f"{prefix}-{uuid.uuid4().hex[:6]}"
