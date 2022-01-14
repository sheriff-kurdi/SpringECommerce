package com.kurdi.springecommerce.domain.entities.productsAggregate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private String name;
    private double price;
/*    @ElementCollection
    //edit table without changing the embeddable class
    @AttributeOverride(
            name = "name",
            column = @Column(name = "filename", nullable = false)
    )
    @CollectionTable(name = "IMAGE")
    protected Set<Image> images = new HashSet<Image>();*/
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    protected List<Category> categories = new ArrayList<>();

}
