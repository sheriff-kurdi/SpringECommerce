package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.Authority;
import com.kurdi.springecommerce.repositories.UserRepository;
import com.kurdi.springecommerce.security.Permissions;
import com.kurdi.springecommerce.security.Roles;
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
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public ResponseEntity<String> register()
    {
        List<Authority> authorities = new ArrayList<>();
        AppUser user = AppUser.builder()
                .username("sheriff")
                .password(passwordEncoder.encode("123"))
                .authorities(authorities)
                .build();

        authorities.add(Authority
                .builder()
                .appUser(user)
                .name(Roles.ADMIN.getRole())
                .build());

        authorities.add(Authority
                .builder()
                .appUser(user)
                .name(Permissions.EmployeeRead.getPermission())
                .build());

        if(userRepository.findUserByUsername(user.getUsername()).isEmpty())
        {
            userRepository.save(user);
        }


        return ResponseEntity.ok(user.getUsername());
    }
}
