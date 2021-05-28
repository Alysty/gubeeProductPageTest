package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.adapter.out.persistence.ProductRepositoryOut;
import com.spring_boot_backend.product.application.ports.in.CreateProductUseCase;
import com.spring_boot_backend.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class CreateProductService implements CreateProductUseCase {


    private ProductRepositoryOut productRepositoryOut;

    @Autowired
    public CreateProductService (ProductRepositoryOut productRepositoryOut){
        Objects.requireNonNull(productRepositoryOut);
        this.productRepositoryOut = productRepositoryOut;
    }
    @Override
    public void createProduct(String productName, String description, Set<String> targetMarketStack, Set<String> technologiesStack) {
        Product product = new Product(productName, description, targetMarketStack, technologiesStack);
        this.productRepositoryOut.insert(product);
    }
}
