package com.spring_boot_backend.product.domain;


import lombok.*;

import java.util.HashSet;
import java.util.Set;


//Lombok annotations auto generating basic code
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product{

    @EqualsAndHashCode.Include
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
    public Product (@NonNull String productName, @NonNull String description,
                    @NonNull Set<String> targetMarketStack, @NonNull Set<String> technologiesStack){
        this.productName = productName;
        this.description = description;
        this.targetMarketStack = targetMarketStack;
        this.technologiesStack = technologiesStack;
    }
}
