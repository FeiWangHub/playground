from pydantic_settings import BaseSettings, SettingsConfigDict


class Settings(BaseSettings):
    model_config = SettingsConfigDict(env_file=".env", env_file_encoding="utf-8")
    app_name: str = "FastAPI Demo"
    env: str = "development"
    log_level: str = "info"
    host: str = "127.0.0.1"
    port: int = 8000


settings = Settings()
