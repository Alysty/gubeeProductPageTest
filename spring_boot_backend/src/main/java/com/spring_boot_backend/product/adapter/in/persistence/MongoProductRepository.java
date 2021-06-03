package com.spring_boot_backend.product.adapter.in.persistence;


import com.spring_boot_backend.product.adapter.in.entity.ProductEntity;
import com.spring_boot_backend.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

//Repository for consulting the database
@Repository
public interface MongoProductRepository extends MongoRepository<ProductEntity, String> {

    //Return all the products that match all the queries below
    List<ProductEntity> findAllByProductNameLikeAndTargetMarketStackInAndTechnologiesStackIn(
            String name, Set<String> targetMarketStack, Set<String> technologiesStack);

    //Return all the products without any markets queries
    List<ProductEntity> findAllByProductNameLikeAndTargetMarketStackIn(
            String name, Set<String> targetMarketStack);

    //Return all the products without any technologies queries
    List<ProductEntity> findAllByProductNameLikeAndTechnologiesStackIn(
            String name, Set<String> technologiesStack);

    //Return all the product without any markets and technologies queries
    List<ProductEntity> findAllByProductNameLike(String name);
}