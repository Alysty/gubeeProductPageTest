package com.spring_boot_backend.product.adapter.in.persistence;


import com.spring_boot_backend.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

//Repository for consulting the database
@Repository
public interface ProductRepositoryIn extends MongoRepository<Product, String> {

    //Return all the products in the database in List format
    List<Product> findAll();

    //Return all the products that match all the queries below
    List<Product> findAllByProductNameLikeAndTargetMarketStackInAndTechnologiesStackIn(
            String name, Set<String> TargetMarketStack, Set<String> TechnologiesStack);

    //Return all the products without any markets queries
    List<Product> findAllByProductNameLikeAndTargetMarketStackIn(
            String name, Set<String> TargetMarketStack);

    //Return all the products without any technologies queries
    List<Product> findAllByProductNameLikeAndTechnologiesStackIn(
            String name, Set<String> TechnologiesStack);

}