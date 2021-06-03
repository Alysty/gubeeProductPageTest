package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.ports.out.UpdateProductUseCase;
import com.spring_boot_backend.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {
    private final ProductRepository productRepository;

    @Override
    public Product updateProduct(UpdateProductRequest updateProductRequest) {
        return productRepository.save(Product.builder()
                .id(updateProductRequest.getId())
                .productName(updateProductRequest.getProductName())
                .description(updateProductRequest.getDescription())
                .targetMarketStack(updateProductRequest.getTargetMarketStack())
                .technologiesStack(updateProductRequest.getTechnologiesStack())
                .build());
    }

}
