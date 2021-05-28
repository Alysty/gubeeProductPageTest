package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.adapter.in.persistence.ProductRepositoryIn;
import com.spring_boot_backend.product.application.ports.out.QueryForProductsUseCase;
import com.spring_boot_backend.product.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QueryForProductsService implements QueryForProductsUseCase {

    @Autowired
    ProductRepositoryIn productRepositoryIn;

    @Override
    public List<Product> searchForProductsUsingAllParameters(String name, Set<String> targetMarketStack, Set<String> technologiesStack) {
        return productRepositoryIn.findAllByProductNameLikeAndTargetMarketStackInAndTechnologiesStackIn(name, targetMarketStack, technologiesStack);
    }

    @Override
    public List<Product> searchForProductsWithoutUsingMarkets(String name, Set<String> targetMarketStack) {
        return productRepositoryIn.findAllByProductNameLikeAndTargetMarketStackIn(name, targetMarketStack);
    }

    @Override
    public List<Product> searchForProductsWithoutUsingTechnologies(String name, Set<String> technologiesStack) {
        return productRepositoryIn.findAllByProductNameLikeAndTechnologiesStackIn(name, technologiesStack);
    }
}
