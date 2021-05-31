package com.spring_boot_backend.product.application.ports.in;

import com.spring_boot_backend.product.domain.Product;

import java.util.List;

public interface GetAllProductsUseCase {
    public List<Product> getAllProducts();
}
