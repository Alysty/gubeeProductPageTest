package com.spring_boot_backend.product.application.ports.out;

import com.spring_boot_backend.product.domain.Product;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Set;

public interface UpdateProductUseCase {
    Product updateProduct(UpdateProductRequest updateProductRequest);
    @Builder
    @Value
    class UpdateProductRequest{
        @NonNull
        String id;
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
