package com.kurdi.springecommerce.repositories;

import com.kurdi.springecommerce.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority, Integer> {
}
