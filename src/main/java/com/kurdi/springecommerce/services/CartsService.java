package com.kurdi.springecommerce.services;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.CartsAggregate.CartItem;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import com.kurdi.springecommerce.dto.cart.EditCartItemDTO;
import com.kurdi.springecommerce.repositories.*;
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

    public Cart ediCart(AppUser user, List<EditCartItemDTO> editCartItemDTOS) {
        clearCart(user);
        Cart userCart = getCart(user);

        for (EditCartItemDTO editCartItemDTO : editCartItemDTOS) {
            if (productsRepository.existsById(editCartItemDTO.getProductId())) {
                Product product = productsRepository.getById(editCartItemDTO.getProductId());
                addToCart(user, product, editCartItemDTO.getQuantity());
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
