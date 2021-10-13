package com.kurdi.springecommerce.domain.entities.CartsAggregate;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
public class CartItem {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Product_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cart_ID")
    private Cart cart;

    private int quantity;

}
