package com.spring_boot_backend.product.application.ports.in;

import com.spring_boot_backend.product.domain.Product;

import java.util.List;
import java.util.Set;

public interface QueryForProductsUseCase {
    public List<Product> searchForProductsUsingAllParameters(String name, Set<String> targetMarketStack, Set<String> technologiesStack);
    public List<Product> searchForProductsWithoutUsingMarkets(String name, Set<String> targetMarketStack);
    public List<Product> searchForProductsWithoutUsingTechnologies(String name, Set<String> technologiesStack);
    public List<Product> searchForProductsWithNameOnly(String name);
}
