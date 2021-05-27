package com.spring_boot_backend.product.domain;


import lombok.EqualsAndHashCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//Spring annotation for mongo collections
@Document
//Lombok annotations auto generating basic code
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Serializable {
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
    public Product (String productName, String description,
                    Set<String> targetMarketStack, Set<String> technologiesStack){
        this.productName = productName;
        this.description = description;
        this.targetMarketStack = targetMarketStack;
        this.technologiesStack = technologiesStack;
    }
}
