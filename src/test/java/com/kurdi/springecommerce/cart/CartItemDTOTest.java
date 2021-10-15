package com.kurdi.springecommerce.cart;

import com.kurdi.springecommerce.dto.cart.CartItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class CartItemDTOTest {
    @Test
    void equalMethodTest() {
        CartItemDTO cartItemDTO1 = CartItemDTO.builder().productId("one").quantity(5).build();
        CartItemDTO cartItemDTO2 = CartItemDTO.builder().productId("one").quantity(5).build();

        boolean actualValue = cartItemDTO1.equals(cartItemDTO2);

        Assertions.assertTrue(actualValue);
    }


}
