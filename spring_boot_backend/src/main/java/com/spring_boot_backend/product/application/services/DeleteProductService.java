package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.adapter.out.persistence.ProductRepositoryOut;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class DeleteProductService {
    private ProductRepositoryOut productRepositoryOut;

    @Autowired
    public DeleteProductService (ProductRepositoryOut productRepositoryOut){
        Objects.requireNonNull(productRepositoryOut);
        this.productRepositoryOut = productRepositoryOut;
    }
}
