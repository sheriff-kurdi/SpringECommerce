package com.kurdi.springecommerce.services;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.CartsAggregate.CartItem;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.repositories.CartsRepository;
import com.kurdi.springecommerce.repositories.ProductsRepository;
import com.kurdi.springecommerce.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartsService {
    @Autowired
    UsersRepository userRepository;
    @Autowired
    CartsRepository cartsRepository;
    @Autowired
    ProductsRepository productsRepository;


    public Cart addToCart(Product product, AppUser user, int quantity) {
        user.getCart().addToCart(product, quantity);
        userRepository.save(user);
        return user.getCart();
    }

    public Cart AddToCartDemo() {
        Product product = Product.builder()
                .name("shirt")
                .price(150)
                .build();
        productsRepository.save(product);
        AppUser user = userRepository.findUserByUsername("sheriff").get();
        Cart cart = addToCart(product, user, 22);
        return cart;
    }

    public Cart getCart(AppUser user) {
        return cartsRepository.findById(user.getCart().getId()).get();
    }


}
