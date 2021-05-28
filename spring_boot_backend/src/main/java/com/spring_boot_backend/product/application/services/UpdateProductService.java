package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.application.ports.in.UpdateProductUseCase;

import java.util.Set;

public class UpdateProductService implements UpdateProductUseCase {
    @Override
    public void updateProduct(String id, String productName, String description, Set<String> targetMarketStack, Set<String> technologiesStack) {

    }
}
