package com.spring_boot_backend.product.adapter.in.persistence;


import com.spring_boot_backend.product.adapter.in.entity.ProductEntity;
import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final MongoProductRepository mongoProductRepository;

    @Override
    public List<Product> findAll() {
        return mongoProductRepository.findAll().stream().map(ProductEntity::toDomain).collect(toList());
    }

    @Override
    public Optional<Product> findById(String id) {
        return mongoProductRepository.findById(id).map(ProductEntity::toDomain);
    }

    @Override
    public List<Product> findAllByFiltering(ProductFilter productFilter) {
        return mongoProductRepository
                .findAllByProductNameLikeAndTargetMarketStackInAndTechnologiesStackIn(
                        productFilter.getName(),
                        productFilter.getTargetMarketStack(),
                        productFilter.getTechnologiesStack())
                .stream().map(ProductEntity::toDomain).collect(toList());
    }

    @Override
    public List<Product> findAllByProductNameLikeAndTargetMarketStackIn(ProductFilter productFilter) {
        return mongoProductRepository
                .findAllByProductNameLikeAndTargetMarketStackIn(
                        productFilter.getName(),
                        productFilter.getTargetMarketStack())
                .stream().map(ProductEntity::toDomain).collect(toList());
    }

    @Override
    public List<Product> findAllByProductNameLikeAndTechnologiesStackIn(ProductFilter productFilter) {
        return mongoProductRepository
                .findAllByProductNameLikeAndTechnologiesStackIn(
                        productFilter.getName(),
                        productFilter.getTechnologiesStack())
                .stream().map(ProductEntity::toDomain).collect(toList());
    }

    @Override
    public List<Product> findAllByProductNameLike(ProductFilter productFilter) {
        return mongoProductRepository
                .findAllByProductNameLike(
                        productFilter.getName())
                .stream().map(ProductEntity::toDomain).collect(toList());
    }

    @Override
    public Product save(Product product) {
        return Optional.ofNullable(product.getId())
                .flatMap(id -> mongoProductRepository.findById(id))
                .map(productEntity -> {
                    productEntity.update(product);
                    return mongoProductRepository.save(productEntity);
                })
                .or(()-> Optional.of(mongoProductRepository.insert(ProductEntity.create(product))))
                .map(ProductEntity::toDomain)
                .get();
    }

    @Override
    public void delete(String id) {
        mongoProductRepository.deleteById(id);
    }
}


