package com.kurdi.springecommerce.repositories;

import com.kurdi.springecommerce.entities.Category;
import com.kurdi.springecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category, String> {
}
