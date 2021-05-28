package com.spring_boot_backend.product.application.ports.in;

import com.spring_boot_backend.product.domain.Product;

import java.util.Set;

public interface UpdateProductUseCase {
    public Product updateProduct(String id, String productName, String description,
                                 Set<String> targetMarketStack, Set<String> technologiesStack);
}
