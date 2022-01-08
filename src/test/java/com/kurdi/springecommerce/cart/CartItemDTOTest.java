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
        boolean expectedValue = true;

        Assertions.assertTrue(actualValue);
    }

    @Test
    void cartItemDTOEqualitySetTest() {
        CartItemDTO cartItemDTO1 = CartItemDTO.builder().productId("2c9210817c83d2bd017c83d30bf90001").quantity(2).build();
        CartItemDTO cartItemDTO2 = CartItemDTO.builder().productId("2c9210817c83d2bd017c83d30bf90001").quantity(2).build();
        CartItemDTO cartItemDTO3 = CartItemDTO.builder().productId("2c9210817c83d2bd017c83d30bf90001").quantity(2).build();


        Set<CartItemDTO> cartItemDTOSet = new HashSet<>();
        cartItemDTOSet.add(cartItemDTO1);
        cartItemDTOSet.add(cartItemDTO2);
        cartItemDTOSet.add(cartItemDTO3);

        int actualValue = cartItemDTOSet.size();
        int expectedValue = 1;

        Assertions.assertEquals(actualValue, expectedValue);
    }
}
