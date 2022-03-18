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
public class CartItemDTO {
    private String productId;
    private int quantity;
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof CartItemDTO)) {
            return false;
        }
        CartItemDTO cartItemDTO = (CartItemDTO) object;
        return this.productId == cartItemDTO.productId;
    }
    @Override
    public int hashCode() {
        return productId.hashCode();
    }


}
