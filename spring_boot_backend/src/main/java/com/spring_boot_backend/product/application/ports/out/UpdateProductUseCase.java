package com.spring_boot_backend.product.application.ports.out;

import com.spring_boot_backend.product.domain.Product;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

public interface UpdateProductUseCase {
    Product updateProduct(UpdateProductRequest updateProductRequest);
    @Builder
    @Value
    class UpdateProductRequest{
        String id;
        String productName;
        String description;
        Set<String> targetMarketStack;
        Set<String> technologiesStack;
    }
}
