package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.Authority;
import com.kurdi.springecommerce.repositories.UsersRepository;
import com.kurdi.springecommerce.security.Permissions;
import com.kurdi.springecommerce.security.Roles;
import com.kurdi.springecommerce.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthController {

    @Autowired
    UsersService usersService;

    @GetMapping("/register")
    public ResponseEntity<AppUser> register() {
        return ResponseEntity.ok(usersService.register());
    }
}
