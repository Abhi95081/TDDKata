from fastapi import FastAPI

from .database import Base, engine
from .routers import auth as auth_router
from .routers import sweets as sweets_router

app = FastAPI(title="Sweet Shop Backend", version="0.1.0")

@app.on_event("startup")
def on_startup():
    Base.metadata.create_all(bind=engine)

app.include_router(auth_router.router)
app.include_router(sweets_router.router)

@app.get("/health", tags=["health"])
def health_check():
    return {"status": "ok"}
