package com.kurdi.springecommerce.domain.entities.CartsAggregate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kurdi.springecommerce.domain.entities.UsersAggregate.AppUser;
import com.kurdi.springecommerce.domain.entities.productsAggregate.Product;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "carts")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})

public class Cart implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @OneToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JsonIgnore
    protected AppUser user;

    @OneToMany(mappedBy = "cart",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    protected Set<CartItem> cartItems = new HashSet<>();

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private double Total;

    public Cart(AppUser user) {
        this.user = user;
    }

    public double getTotal() {
        for (CartItem cartItem : cartItems) {
            this.Total += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        }
        return this.Total;
    }

    public void addToCart(Product product, int quantity) {
        CartItem cartItem = new CartItem(product, this, quantity);
        this.cartItems.add(cartItem);
    }


}


