package com.kurdi.springecommerce.repositories;

import com.kurdi.springecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, String> {
}
