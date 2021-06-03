package com.spring_boot_backend.product.adapter.in.stub;

import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.domain.Product;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class ProductRepositoryMemory implements ProductRepository {
    Map<String, Product> productDB = new HashMap<>();

    @Override
    public List<Product> findAll() {
        return productDB.values().stream().collect(toList());
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productDB.get(id));
    }

    @Override
    public List<Product> findAllByFiltering(ProductFilter productFilter) {
        return productDB.values().stream().filter(product ->
                product.getProductName().toLowerCase().contains(productFilter.getName().toLowerCase())
                        && product.getTargetMarketStack().containsAll(productFilter.getTargetMarketStack())
                        && product.getTechnologiesStack().containsAll(productFilter.getTechnologiesStack())
        ).collect(toList());
    }

    @Override
    public List<Product> findAllByProductNameLikeAndTargetMarketStackIn(ProductFilter productFilter) {
        return productDB.values().stream().filter(product ->
                product.getProductName().toLowerCase().contains(productFilter.getName().toLowerCase())
                        && product.getTargetMarketStack().containsAll(productFilter.getTargetMarketStack())
        ).collect(toList());
    }

    @Override
    public List<Product> findAllByProductNameLikeAndTechnologiesStackIn(ProductFilter productFilter) {
        return productDB.values().stream().filter(product ->
                product.getProductName().toLowerCase().contains(productFilter.getName().toLowerCase())
                        && product.getTechnologiesStack().containsAll(productFilter.getTechnologiesStack())
        ).collect(toList());
    }

    @Override
    public List<Product> findAllByProductNameLike(ProductFilter productFilter) {
        return productDB.values().stream().filter(product ->
                product.getProductName().toLowerCase().contains(productFilter.getName().toLowerCase())
        ).collect(toList());
    }

    @Override
    public Product save(Product product) {
        if(product.getId() == null) {
            product.setId(UUID.randomUUID().toString());
        }
        this.productDB.put(product.getId(), product);
        return product;
    }

    @Override
    public void delete(String id) {
        this.productDB.remove(id);
    }
}
