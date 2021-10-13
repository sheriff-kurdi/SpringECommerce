package com.kurdi.springecommerce.services;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CartService {

    public Cart addToCart(String userId) {
        AppUser use = new AppUser();
        use.getCart();
        return new Cart();
    }
}
