package com.spring_boot_backend.product.application.ports.in;


import com.spring_boot_backend.product.domain.Product;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository {

    //Return all the products in the database in List format
    List<Product> findAll();

    //Return a product using its id
    Optional<Product> findById(String id);

    //Return all the products that match all the queries below
    List<Product> findAllByFiltering(
            ProductFilter productFilter);

    //Return all the products without any markets queries
    List<Product> findAllByProductNameLikeAndTargetMarketStackIn(
            ProductFilter productFilter);

    //Return all the products without any technologies queries
    List<Product> findAllByProductNameLikeAndTechnologiesStackIn(
            ProductFilter productFilter);

    //Return all the product without any markets and technologies queries
    List<Product> findAllByProductNameLike(ProductFilter productFilter);

    Product save(Product product);

    void delete(String id);

    @Builder
    @Value
    class ProductFilter{
        @NonNull
        String name;
        @NonNull
        Set<String> targetMarketStack;
        @NonNull
        Set<String> technologiesStack;
    }
}