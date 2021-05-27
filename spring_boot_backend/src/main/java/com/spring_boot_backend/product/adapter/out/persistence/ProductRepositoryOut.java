package com.spring_boot_backend.product.adapter.out.persistence;


import com.spring_boot_backend.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
// Repository for inserting into the database
@Repository
public interface ProductRepositoryOut extends MongoRepository<Product, String> {

}