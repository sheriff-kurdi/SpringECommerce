package com.kurdi.springecommerce.domain.entities.productsAggregate;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "name_ar")
    private String nameAr;
    private String description;
    private double price;
    private int stock;
    @Column(name = "available_stock")
    private int availableStock = stock;
    private boolean activation = true;
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
        name = "category_product",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")

)
    protected Set<Category> categories = new HashSet<>();


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
