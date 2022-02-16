package com.kurdi.springecommerce.domain.entities.OrdersAggregate;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @ElementCollection
    //edit table without changing the embeddable class
    @AttributeOverride(
            name = "product_id",
            column = @Column(nullable = false)
    )
    @CollectionTable(name = "CART_ITEMS")
    protected Set<CartItem> cartItem = new HashSet<>();

    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            //tells Hibernate that you want to permanently
            //remove a orderItem when it’s removed from the collection.
            orphanRemoval = true)
    protected Set<OrderItem> orderItems = new HashSet<>();
}
