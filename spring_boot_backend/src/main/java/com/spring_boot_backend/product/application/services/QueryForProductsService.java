package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.ports.in.QueryForProductsUseCase;
import com.spring_boot_backend.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QueryForProductsService implements QueryForProductsUseCase {

    private final ProductRepository productRepository;

    @Override
    public List<Product> searchForProductsUsingAllParameters(ProductRepository.ProductFilter productFilter) {
        String name = productFilter.getName();
        Set<String> targetMarketStack = productFilter.getTargetMarketStack();
        Set<String> technologiesStack = productFilter.getTechnologiesStack();
        if (name == null) {
            name = "";
        }
        var result = List.<Product>of();

        if ((targetMarketStack == null || targetMarketStack.isEmpty()) && (technologiesStack == null || technologiesStack.isEmpty())) {
            result = this.searchForProductsWithNameOnly(name);
        } else if (technologiesStack == null || technologiesStack.isEmpty())  {
            result = this.searchForProductsWithoutUsingTechnologies(name, targetMarketStack);
        } else if (targetMarketStack == null || targetMarketStack.isEmpty()) {
            result = this.searchForProductsWithoutUsingMarkets(name, technologiesStack);
        }else {
            System.out.println("Name: " + name + "\nMarkets: " + targetMarketStack.stream().findFirst() + "\nTechnologies: " + technologiesStack.stream().findFirst());
            result = this.productRepository.findAllByFiltering(ProductRepository
                    .ProductFilter.builder()
                    .name(name)
                    .targetMarketStack(targetMarketStack)
                    .technologiesStack(technologiesStack)
                    .build());
        }
        return result;
    }

    @Override
    public List<Product> searchForProductsWithoutUsingTechnologies(String name, Set<String> targetMarketStack) {
        return productRepository.findAllByProductNameLikeAndTargetMarketStackIn(ProductRepository
                .ProductFilter.builder()
                .name(name)
                .technologiesStack(new HashSet<>())
                .targetMarketStack(targetMarketStack)
                .build());
    }

    @Override
    public List<Product> searchForProductsWithoutUsingMarkets(String name, Set<String> technologiesStack) {
        return productRepository.findAllByProductNameLikeAndTechnologiesStackIn(ProductRepository
                .ProductFilter.builder()
                .name(name)
                .targetMarketStack(new HashSet<>())
                .technologiesStack(technologiesStack)
                .build());
    }

    @Override
    public List<Product> searchForProductsWithNameOnly(String name) {
        return productRepository.findAllByProductNameLike(ProductRepository
                .ProductFilter.builder()
                .name(name)
                .technologiesStack(new HashSet<>())
                .targetMarketStack(new HashSet<>())
                .build());
    }

    @Override
    public Optional<Product> getById(String id) {
        return productRepository.findById(id);
    }


}
