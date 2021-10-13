package com.kurdi.springecommerce.domain.entities.CartsAggregate;

import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToMany(mappedBy = "cart",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    protected Set<CartItem> cartItems = new HashSet<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private double Total;

    public double getTotal()
    {
        for (CartItem cartItem : cartItems)
        {
            this.Total += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        }
        return  this.Total;
    }

    public void addToCart(Product product, int quantity)
    {
        CartItem cartItem = new CartItem(product, this, quantity);
        this.cartItems.add(cartItem);
    }


}


