package com.spring_boot_backend.product.application.ports.out;


import java.util.Set;

public interface CreateProductUseCase {
    public void createProduct(String productName, String description,
                              Set<String> targetMarketStack, Set<String> technologiesStack);
}
