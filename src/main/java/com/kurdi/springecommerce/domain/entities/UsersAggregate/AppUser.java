package com.kurdi.springecommerce.domain.entities.UsersAggregate;

import com.kurdi.springecommerce.domain.entities.CartsAggregate.Cart;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "appUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Authority> authorities = new ArrayList<>();
    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.PERSIST
    )
    private Address shippingAddress;

    @OneToOne(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private Cart cart;

}
