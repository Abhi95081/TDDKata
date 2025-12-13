from typing import List, Optional

from fastapi import APIRouter, Depends, status
from sqlalchemy.orm import Session

from .. import schemas
from ..deps import get_current_admin, get_current_user, get_db
from ..services.sweet_service import SweetService

router = APIRouter(prefix="/sweets", tags=["sweets"])

@router.get("/", response_model=List[schemas.SweetOut])
def list_sweets(
    name: Optional[str] = None,
    category: Optional[str] = None,
    max_price: Optional[float] = None,
    min_price: Optional[float] = None,
    db: Session = Depends(get_db),
):
    service = SweetService(db)
    return service.list_sweets(name=name, category=category, max_price=max_price, min_price=min_price)

@router.get("/{sweet_id}", response_model=schemas.SweetOut)
def get_sweet(sweet_id: int, db: Session = Depends(get_db)):
    service = SweetService(db)
    return service.get_sweet(sweet_id)

@router.post("/", response_model=schemas.SweetOut, status_code=status.HTTP_201_CREATED)
def create_sweet(
    sweet_in: schemas.SweetCreate,
    db: Session = Depends(get_db),
    _: None = Depends(get_current_admin),
):
    service = SweetService(db)
    return service.create_sweet(sweet_in)

@router.put("/{sweet_id}", response_model=schemas.SweetOut)
def update_sweet(
    sweet_id: int,
    sweet_in: schemas.SweetUpdate,
    db: Session = Depends(get_db),
    _: None = Depends(get_current_admin),
):
    service = SweetService(db)
    return service.update_sweet(sweet_id, sweet_in)

@router.delete("/{sweet_id}", status_code=status.HTTP_204_NO_CONTENT)
def delete_sweet(
    sweet_id: int,
    db: Session = Depends(get_db),
    _: None = Depends(get_current_admin),
):
    service = SweetService(db)
    service.delete_sweet(sweet_id)
    return None

@router.post("/{sweet_id}/purchase", response_model=schemas.PurchaseResponse)
def purchase_sweet(
    sweet_id: int,
    purchase: schemas.PurchaseRequest,
    db: Session = Depends(get_db),
    _: None = Depends(get_current_user),
):
    service = SweetService(db)
    return service.purchase(sweet_id, purchase.quantity)
