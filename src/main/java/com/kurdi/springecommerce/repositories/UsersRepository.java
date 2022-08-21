package com.kurdi.springecommerce.repositories;

import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findUserByEmail(String u);
}
