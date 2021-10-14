package com.kurdi.springecommerce.dto.cart;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditCartItemDTO {
    private String productId;
    private int quantity;
}
