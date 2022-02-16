package com.kurdi.springecommerce.infrastructure.services;

import com.kurdi.springecommerce.domain.dto.cart.CartItemDTO;
import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.CartsAggregate.CartItem;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.infrastructure.repositories.CartItemsRepository;
import com.kurdi.springecommerce.infrastructure.repositories.CartsRepository;
import com.kurdi.springecommerce.infrastructure.repositories.ProductsRepository;
import com.kurdi.springecommerce.infrastructure.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CartsService {
    @Autowired
    UsersRepository userRepository;
    @Autowired
    CartsRepository cartsRepository;
    @Autowired
    ProductsRepository productsRepository;
    @Autowired
    CartItemsRepository cartItemsRepository;


    public Cart addRangeToCart(AppUser user, Set<CartItemDTO> cartItemDTOSet) {
        for (CartItemDTO cartItemDTO : cartItemDTOSet) {
            Product product = productsRepository.getById(cartItemDTO.getProductId());
            addToCart(user, product, cartItemDTO.getQuantity());
        }
        return user.getCart();

    }

    public Cart addToCart(AppUser user, Product product, int quantity) {
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
        Cart cart = addToCart(user, product, 22);
        return cart;
    }

    public Cart getCart(AppUser user) {
        return cartsRepository.findById(user.getCart().getId()).get();
    }

    public Cart ediCart(AppUser user, List<CartItemDTO> cartItemDTOS) {
        clearCart(user);
        Cart userCart = getCart(user);

        for (CartItemDTO cartItemDTO : cartItemDTOS) {
            if (productsRepository.existsById(cartItemDTO.getProductId())) {
                Product product = productsRepository.getById(cartItemDTO.getProductId());
                addToCart(user, product, cartItemDTO.getQuantity());
            }
        }
        return getCart(user);
    }

    public Cart clearCart(AppUser user) {
        Cart userCart = getCart(user);
        Set<CartItem> cartItemsToRemove = userCart.getCartItems();
        userCart.getCartItems().clear();
        for (CartItem cartItem : cartItemsToRemove) {
/*
            userCart.getCartItems().remove(cartItem);
*/
            cartItemsRepository.delete(cartItem);

        }


        return getCart(user);
    }


}
