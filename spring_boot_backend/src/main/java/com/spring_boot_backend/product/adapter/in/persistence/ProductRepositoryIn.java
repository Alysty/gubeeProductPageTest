package com.spring_boot_backend.product.adapter.in.persistence;


import com.spring_boot_backend.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryIn extends MongoRepository<Product, String> {

    List<Product> findAll();
}