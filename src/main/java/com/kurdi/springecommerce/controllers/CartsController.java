package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.CartsAggregate.CartItem;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.repositories.UsersRepository;
import com.kurdi.springecommerce.services.CartsService;
import com.kurdi.springecommerce.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Api/Carts/")
public class CartsController {
    @Autowired
    CartsService cartsService;
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/addToCart")
    public ResponseEntity<Cart> addToCart() {
        Cart cart = cartsService.AddToCartDemo();
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/")
    public ResponseEntity<Cart> get() {
        AppUser user = usersRepository.findUserByUsername("sheriff").get();
        Cart cart = user.getCart();
        return ResponseEntity.ok(cart);
    }
}
