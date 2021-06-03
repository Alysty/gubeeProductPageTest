package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.ports.out.DeleteProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductService implements DeleteProductUseCase {
    private final ProductRepository productRepository;

    @Override
    public void deleteProduct(String id) {
        productRepository.delete(id);
    }
}
