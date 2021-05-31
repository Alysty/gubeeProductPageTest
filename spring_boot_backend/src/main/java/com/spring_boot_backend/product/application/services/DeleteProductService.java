package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.adapter.out.persistence.ProductRepositoryOut;
import com.spring_boot_backend.product.application.ports.out.DeleteProductUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeleteProductService implements DeleteProductUseCase {
    private ProductRepositoryOut productRepositoryOut;

    @Autowired
    public void DeleteProductService(ProductRepositoryOut productRepositoryOut){
        Objects.requireNonNull(productRepositoryOut);
        this.productRepositoryOut = productRepositoryOut;
    }

    @Override
    public void deleteProduct(String id) {
        productRepositoryOut.deleteById(id);
    }
}
