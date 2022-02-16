package com.kurdi.springecommerce.infrastructure.repositories;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, String> {
}
