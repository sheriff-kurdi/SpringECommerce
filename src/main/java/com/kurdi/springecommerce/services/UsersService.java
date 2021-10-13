package com.kurdi.springecommerce.services;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.Authority;
import com.kurdi.springecommerce.repositories.UsersRepository;
import com.kurdi.springecommerce.security.Permissions;
import com.kurdi.springecommerce.security.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsersService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UsersRepository userRepository;

    public AppUser register() {
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

        if (userRepository.findUserByUsername(user.getUsername()).isEmpty()) {
            userRepository.save(user);
            user.setCart(new Cart(user));
            userRepository.save(user);

        }

        return user;
    }
}
