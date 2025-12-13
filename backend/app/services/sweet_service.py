from typing import List, Optional

from fastapi import HTTPException, status
from sqlalchemy.orm import Session

from .. import models, schemas


class SweetService:
    def __init__(self, db: Session):
        self.db = db

    def list_sweets(
        self,
        name: Optional[str] = None,
        category: Optional[str] = None,
        max_price: Optional[float] = None,
        min_price: Optional[float] = None,
    ) -> List[models.Sweet]:
        query = self.db.query(models.Sweet)
        if name:
            query = query.filter(models.Sweet.name.ilike(f"%{name}%"))
        if category:
            query = query.filter(models.Sweet.category.ilike(f"%{category}%"))
        if max_price is not None:
            query = query.filter(models.Sweet.price <= max_price)
        if min_price is not None:
            query = query.filter(models.Sweet.price >= min_price)
        return query.order_by(models.Sweet.name).all()

    def get_sweet(self, sweet_id: int) -> models.Sweet:
        sweet = self.db.query(models.Sweet).filter(models.Sweet.id == sweet_id).first()
        if not sweet:
            raise HTTPException(status_code=status.HTTP_404_NOT_FOUND, detail="Sweet not found")
        return sweet

    def create_sweet(self, sweet_in: schemas.SweetCreate) -> models.Sweet:
        exists = self.db.query(models.Sweet).filter(models.Sweet.name == sweet_in.name).first()
        if exists:
            raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Sweet name already exists")
        sweet = models.Sweet(**sweet_in.model_dump())
        self.db.add(sweet)
        self.db.commit()
        self.db.refresh(sweet)
        return sweet

    def update_sweet(self, sweet_id: int, sweet_in: schemas.SweetUpdate) -> models.Sweet:
        sweet = self.get_sweet(sweet_id)
        for field, value in sweet_in.model_dump(exclude_unset=True).items():
            setattr(sweet, field, value)
        self.db.add(sweet)
        self.db.commit()
        self.db.refresh(sweet)
        return sweet

    def delete_sweet(self, sweet_id: int) -> None:
        sweet = self.get_sweet(sweet_id)
        self.db.delete(sweet)
        self.db.commit()

    def purchase(self, sweet_id: int, qty: int) -> schemas.PurchaseResponse:
        sweet = self.get_sweet(sweet_id)
        if sweet.quantity < qty:
            raise HTTPException(status_code=status.HTTP_400_BAD_REQUEST, detail="Not enough stock")
        sweet.quantity -= qty
        self.db.add(sweet)
        self.db.commit()
        self.db.refresh(sweet)
        return schemas.PurchaseResponse(
            sweet_id=sweet.id,
            purchased=qty,
            remaining=sweet.quantity,
            message="Purchase completed",
        )
