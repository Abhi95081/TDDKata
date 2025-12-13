# Sweet Shop Management System – Backend (FastAPI)

## What This Backend Does

We are building the backend for a Sweet Shop Management System using FastAPI. It exposes RESTful APIs that handle authentication, inventory, and purchasing so any frontend (e.g., Android/Jetpack Compose) can consume it securely.

### Purpose
- Manage sweet shop inventory and availability
- Authenticate users with JWT and enforce roles (`USER`, `ADMIN`)
- Apply business rules for purchasing and stock control
- Persist data in a real database (SQLite by default; PostgreSQL-ready)
- Provide reliable APIs for frontend clients

### Key Features
- **Auth**: Register/login with JWT; roles gate admin actions
- **Inventory**: Add/update/delete sweets (admin); list/search sweets
- **Purchase**: Safe stock decrement; block purchases when out of stock; protect concurrent requests

### Architecture
- FastAPI routers for HTTP layer
- Services for business rules
- SQLAlchemy models for persistence
- Pydantic schemas for validation/serialization
- Dependencies for auth, DB sessions, and role checks

### Technology Stack
- FastAPI
- SQLAlchemy
- SQLite (dev) / PostgreSQL (prod ready)
- JWT via `python-jose`
- Password hashing via `passlib`
- Pytest for tests

## Quickstart
1. Create/activate a virtual env.
2. Install deps: `pip install -r requirements.txt`.
3. Run dev server: `uvicorn app.main:app --reload`.
4. Open docs at `http://localhost:8000/docs`.

## API Surfaces (high level)
- **Auth**: `/auth/register`, `/auth/login`
- **Sweets**: `/sweets` (list/search), `/sweets/{id}`, `/sweets/{id}/purchase`
- **Admin**: `/sweets` (POST), `/sweets/{id}` (PUT/DELETE)

## Notes
- Default DB: SQLite at `app.db` in project root.
- Replace with PostgreSQL by updating `DATABASE_URL` in `app/database.py`.
- Tokens use HS256; update `SECRET_KEY` in `app/auth.py` for production.
