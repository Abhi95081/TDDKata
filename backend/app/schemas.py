from datetime import datetime
from typing import Optional

from pydantic import BaseModel, Field


class Token(BaseModel):
    access_token: str
    token_type: str = "bearer"


class TokenData(BaseModel):
    username: Optional[str] = None


class UserBase(BaseModel):
    username: str = Field(..., min_length=3, max_length=50)


class UserCreate(UserBase):
    password: str = Field(..., min_length=6, max_length=128)
    role: str = Field("USER", pattern="^(USER|ADMIN)$")


class UserOut(UserBase):
    id: int
    role: str
    created_at: datetime

    model_config = {"from_attributes": True}


class SweetBase(BaseModel):
    name: str = Field(..., min_length=1, max_length=100)
    category: Optional[str] = Field(None, max_length=50)
    price: float = Field(..., ge=0)
    quantity: int = Field(..., ge=0)
    image_url: Optional[str] = Field(None, max_length=255)


class SweetCreate(SweetBase):
    pass


class SweetUpdate(BaseModel):
    name: Optional[str] = Field(None, min_length=1, max_length=100)
    category: Optional[str] = Field(None, max_length=50)
    price: Optional[float] = Field(None, ge=0)
    quantity: Optional[int] = Field(None, ge=0)
    image_url: Optional[str] = Field(None, max_length=255)


class SweetOut(SweetBase):
    id: int
    created_at: datetime
    updated_at: datetime

    model_config = {"from_attributes": True}


class PurchaseRequest(BaseModel):
    quantity: int = Field(1, ge=1)


class PurchaseResponse(BaseModel):
    sweet_id: int
    purchased: int
    remaining: int
    message: str
