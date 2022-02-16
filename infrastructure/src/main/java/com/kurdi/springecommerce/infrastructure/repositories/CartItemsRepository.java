package com.kurdi.springecommerce.infrastructure.repositories;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItem, String> {
}
