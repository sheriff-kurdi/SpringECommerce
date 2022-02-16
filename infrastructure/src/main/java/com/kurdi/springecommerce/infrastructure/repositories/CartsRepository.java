package com.kurdi.springecommerce.infrastructure.repositories;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartsRepository extends JpaRepository<Cart, String> {
}
