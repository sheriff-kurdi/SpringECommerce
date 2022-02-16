package com.kurdi.springecommerce.infrastructure.repositories;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, String> {
}
