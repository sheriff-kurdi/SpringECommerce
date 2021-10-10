package com.kurdi.springecommerce.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Builder
@Embeddable
public class CartItem {
    private String name;
    private double price;
    private String productId;
}
