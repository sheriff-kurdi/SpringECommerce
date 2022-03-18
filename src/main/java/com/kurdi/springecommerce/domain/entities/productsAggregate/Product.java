package com.kurdi.springecommerce.domain.entities.productsAggregate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private String description;
    private double price;
/*    @ElementCollection
    //edit table without changing the embeddable class
    @AttributeOverride(
            name = "name",
            column = @Column(name = "filename", nullable = false)
    )
    @CollectionTable(name = "IMAGE")
    protected Set<Image> images = new HashSet<Image>();*/
@JsonManagedReference
@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(
        name = "CATEGORY_Product",
        joinColumns = @JoinColumn(name = "PRODUCT_ID"),
        inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")

)
    protected List<Category> categories = new ArrayList<>();


    public Product addCategory(Category category)
    {
        category.getProducts().add(this);
        this.getCategories().add(category);
        return this;
    }
    public Product removeCategory(Category category)
    {
        category.getProducts().remove(this);
        this.getCategories().remove(category);
        return this;
    }
}
