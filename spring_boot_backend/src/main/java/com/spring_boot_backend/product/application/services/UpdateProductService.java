package com.spring_boot_backend.product.application.services;

import com.spring_boot_backend.product.adapter.out.persistence.ProductRepositoryOut;
import com.spring_boot_backend.product.application.ports.out.UpdateProductUseCase;
import com.spring_boot_backend.product.domain.Product;
import com.spring_boot_backend.shared.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UpdateProductService implements UpdateProductUseCase {
    @Autowired
    ProductRepositoryOut productRepositoryOut;
    @Override
    public Product updateProduct(String id, String productName, String description, Set<String> targetMarketStack, Set<String> technologiesStack) {
        Product updatedProduct = findById(id);
        updatedProduct.setProductName(productName);
        updatedProduct.setDescription(description);
        updatedProduct.setTargetMarketStack(targetMarketStack);
        updatedProduct.setTechnologiesStack(technologiesStack);
        return productRepositoryOut.save(updatedProduct);
    }
    public Product findById(String id) {
        Optional<Product> product = productRepositoryOut.findById(id);
        return product.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}
