package com.leonardo.product.category;


import com.leonardo.product.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Category {


    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    // remove todos os produtos quando excluido a category
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
