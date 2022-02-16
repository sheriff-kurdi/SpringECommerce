package com.kurdi.springecommerce.web.controllers;

import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.infrastructure.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    UsersService usersService;

    @GetMapping("/register")
    public ResponseEntity<AppUser> register() {
        return ResponseEntity.ok(usersService.register());
    }
}
