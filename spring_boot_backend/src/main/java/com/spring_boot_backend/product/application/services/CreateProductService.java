package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.ports.out.CreateProductUseCase;
import com.spring_boot_backend.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {


    private final ProductRepository productRepository;


    @Override
    public Product createProduct(CreateProductRequest createProductRequest) {
        Product product = new Product(createProductRequest.getProductName(),
                createProductRequest.getDescription(),
                createProductRequest.getTargetMarketStack(),
                createProductRequest.getTechnologiesStack());
        return this.productRepository.save(product);
    }
}
