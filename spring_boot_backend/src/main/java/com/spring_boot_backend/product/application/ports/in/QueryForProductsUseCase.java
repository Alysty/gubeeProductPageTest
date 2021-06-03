package com.spring_boot_backend.product.application.ports.in;

import com.spring_boot_backend.product.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QueryForProductsUseCase {
    List<Product> searchForProductsUsingAllParameters(ProductRepository.ProductFilter productFilter);
    List<Product> searchForProductsWithoutUsingMarkets(String name, Set<String> targetMarketStack);
    List<Product> searchForProductsWithoutUsingTechnologies(String name, Set<String> technologiesStack);
    List<Product> searchForProductsWithNameOnly(String name);
    Optional<Product> getById(String id);
}
