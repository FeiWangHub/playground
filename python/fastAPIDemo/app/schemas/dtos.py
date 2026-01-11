from typing import List, Optional
from pydantic import BaseModel

class ModelListDTO(BaseModel):
    models: List[str]

class GenerateResultDTO(BaseModel):
    result: str

class JobDTO(BaseModel):
    id: str
    status: str
    name: Optional[str] = None

class DeploymentDTO(BaseModel):
    id: str
    status: str
    name: Optional[str] = None

class NotebookDTO(BaseModel):
    id: str
    title: str

class KernelListDTO(BaseModel):
    kernels: List[str]

class AppDTO(BaseModel):
    id: str
    name: str
    status: Optional[str] = None
