from datetime import datetime
from sqlalchemy import Column, DateTime, Enum, Float, Integer, String
from sqlalchemy.orm import validates

from .database import Base


class User(Base):
    __tablename__ = "users"

    id = Column(Integer, primary_key=True, index=True)
    username = Column(String(50), unique=True, index=True, nullable=False)
    hashed_password = Column(String(255), nullable=False)
    role = Column(Enum("USER", "ADMIN", name="user_roles"), default="USER", nullable=False)
    created_at = Column(DateTime, default=datetime.utcnow, nullable=False)

    @validates("username")
    def validate_username(self, key, value):
        if not value:
            raise ValueError("Username must not be empty")
        return value


class Sweet(Base):
    __tablename__ = "sweets"

    id = Column(Integer, primary_key=True, index=True)
    name = Column(String(100), unique=True, index=True, nullable=False)
    category = Column(String(50), index=True, nullable=True)
    price = Column(Float, nullable=False)
    quantity = Column(Integer, nullable=False, default=0)
    image_url = Column(String(255), nullable=True)
    created_at = Column(DateTime, default=datetime.utcnow, nullable=False)
    updated_at = Column(DateTime, default=datetime.utcnow, onupdate=datetime.utcnow, nullable=False)

    @validates("name")
    def validate_name(self, key, value):
        if not value:
            raise ValueError("Name must not be empty")
        return value

    @validates("price")
    def validate_price(self, key, value):
        if value is None or value < 0:
            raise ValueError("Price must be non-negative")
        return value

    @validates("quantity")
    def validate_quantity(self, key, value):
        if value is None or value < 0:
            raise ValueError("Quantity must be non-negative")
        return value
