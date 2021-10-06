package com.kurdi.springecommerce.repositories;

import com.kurdi.springecommerce.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findUserByUsername(String u);
}
