package com.spring_boot_backend.product.application.ports.out;


import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;

public interface CreateProductUseCase {
    void createProduct(CreateProductRequest createProductRequest);

    @Builder
    @Value
    class CreateProductRequest{
        @NonNull
        String productName;
        @NonNull
        String description;
        @NonNull
        Set<String> targetMarketStack;
        @NonNull
        Set<String> technologiesStack;
    }
}
