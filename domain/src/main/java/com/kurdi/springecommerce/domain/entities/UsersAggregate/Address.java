package com.kurdi.springecommerce.domain.entities.UsersAggregate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String street;
    @OneToOne(
            fetch = FetchType.LAZY,
            //Lazy loading with proxies only works if the association is non-optional. This is
            //often a surprise for developers new to JPA. The default for @OneToOne is Fetch-Type.EAGER
            optional = false
    )
    protected AppUser user;
}
