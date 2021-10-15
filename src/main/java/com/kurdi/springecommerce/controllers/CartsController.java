package com.kurdi.springecommerce.controllers;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.dto.cart.CartItemDTO;
import com.kurdi.springecommerce.repositories.UsersRepository;
import com.kurdi.springecommerce.services.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("Api/Carts/")
public class CartsController {
    @Autowired
    CartsService cartsService;
    @Autowired
    UsersRepository usersRepository;


    @PostMapping("/addToCart")
    public ResponseEntity<Cart> addToCart(@RequestBody Set<CartItemDTO> cartItemDTOSet) {
        AppUser user = usersRepository.findUserByUsername("sheriff").get();

        Cart cart = cartsService.addRangeToCart(user, cartItemDTOSet);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/")
    public ResponseEntity<Cart> get() {
        AppUser user = usersRepository.findUserByUsername("sheriff").get();
        Cart cart = user.getCart();
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/")
    public ResponseEntity<Cart> delete() {
        AppUser user = usersRepository.findUserByUsername("sheriff").get();
        Cart cart = cartsService.clearCart(user);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/")
    public ResponseEntity<Cart> edit(@RequestBody List<CartItemDTO> cartItemDTOS) {
        AppUser user = usersRepository.findUserByUsername("sheriff").get();
        Cart updatedCart = cartsService.ediCart(user, cartItemDTOS);
        return ResponseEntity.ok(updatedCart);
    }
}
