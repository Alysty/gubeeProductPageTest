package com.spring_boot_backend.product.adapter.in.web;

import com.spring_boot_backend.product.application.ports.out.CreateProductUseCase;
import com.spring_boot_backend.product.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(value = "/products")
public class ProductPostController {

    CreateProductUseCase createProductUseCase;
    public ProductPostController(CreateProductUseCase createProductUseCase){
        Objects.requireNonNull(createProductUseCase);
        this.createProductUseCase = createProductUseCase;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> insert(@RequestBody Product product){
        return new ResponseEntity( createProductUseCase.createProduct(CreateProductUseCase.CreateProductRequest.builder()
                .productName(product.getProductName())
                .description(product.getDescription())
                .targetMarketStack(product.getTargetMarketStack())
                .technologiesStack(product.getTechnologiesStack())
                .build()
        ), HttpStatus.CREATED);
    }

}
