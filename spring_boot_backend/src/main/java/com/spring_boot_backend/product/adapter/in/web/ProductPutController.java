package com.spring_boot_backend.product.adapter.in.web;

import com.spring_boot_backend.product.application.ports.out.UpdateProductUseCase;
import com.spring_boot_backend.product.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/products")
public class ProductPutController {

    UpdateProductUseCase updateProductUseCase;
    public ProductPutController(UpdateProductUseCase updateProductUseCase){
        Objects.requireNonNull(updateProductUseCase);
        this.updateProductUseCase = updateProductUseCase;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Product product){
        return new ResponseEntity(updateProductUseCase.updateProduct(UpdateProductUseCase.UpdateProductRequest.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .targetMarketStack(product.getTargetMarketStack())
                .technologiesStack(product.getTechnologiesStack())
                .build()
        ), HttpStatus.OK);
    }
}
