package com.spring_boot_backend.product.adapter.in.entity;


import com.spring_boot_backend.product.domain.Product;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//Spring annotation for mongo collections
@Document
//Lombok annotations auto generating basic code
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    // Id must be null when inserting in database for auto generation of the ID
    @EqualsAndHashCode.Include
    @Id
    private String id = null;
    @NonNull
    private String productName;
    @NonNull
    private String description;
    @NonNull
    private Set<String> targetMarketStack = new HashSet<>();
    @NonNull
    private Set<String> technologiesStack = new HashSet<>();

    //No id constructor for inserting in the database where the id is auto generated
    public ProductEntity(@NonNull String productName, @NonNull String description,
                         @NonNull Set<String> targetMarketStack, @NonNull Set<String> technologiesStack){
        this.productName = productName;
        this.description = description;
        this.targetMarketStack = targetMarketStack;
        this.technologiesStack = technologiesStack;
    }
    public Product toDomain(){
        return Product.builder()
                .id(this.id)
                .productName(this.productName)
                .description(this.description)
                .targetMarketStack(this.targetMarketStack)
                .technologiesStack(this.technologiesStack)
                .build();
    }
    static public ProductEntity create(Product product){
        return ProductEntity.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .targetMarketStack(product.getTargetMarketStack())
                .technologiesStack(product.getTechnologiesStack())
                .build();
    }
    public void update(Product product){
        this.id = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.targetMarketStack = product.getTargetMarketStack();
        this.technologiesStack = product.getTechnologiesStack();
    }
}
