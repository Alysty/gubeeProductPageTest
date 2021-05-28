package com.spring_boot_backend.product.application.ports.in;

import java.util.Set;

public interface UpdateProductUseCase {
    public void updateProduct(String id, String productName, String description,
                              Set<String> targetMarketStack, Set<String> technologiesStack);
}
