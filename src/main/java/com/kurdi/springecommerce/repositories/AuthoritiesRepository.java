package com.kurdi.springecommerce.repositories;

import com.kurdi.springecommerce.domain.entities.UsersAggregate.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<Authority, Integer> {
}
