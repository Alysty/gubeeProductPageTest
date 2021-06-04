package com.spring_boot_backend.product.adapter.out.web;

import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.ports.in.QueryForProductsUseCase;
import com.spring_boot_backend.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/products")
public class ProductGetController {

    QueryForProductsUseCase queryForProductsUseCase;
    public ProductGetController(QueryForProductsUseCase queryForProductsUseCase){
        Objects.requireNonNull(queryForProductsUseCase);
        this.queryForProductsUseCase = queryForProductsUseCase;
    }

    // Mapping for getting all the possible searches for products in the database
    @GetMapping
    public ResponseEntity<List<Product>> getRequestForAllSearches(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "markets", required = false) Set<String> markets,
            @RequestParam(name = "technologies", required = false) Set<String> technologies) {

        var productList = queryForProductsUseCase.searchForProductsUsingAllParameters(ProductRepository.ProductFilter
                .builder()
                .name(name)
                .targetMarketStack(markets)
                .technologiesStack(technologies)
                .build());
        ResponseEntity<List<Product>> response = ResponseEntity.notFound().build();
        if (!productList.isEmpty()) {
            response = ResponseEntity.ok().body(productList);
        }
        return response;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getSpecificProduct(@PathVariable String id){
        return ResponseEntity.ok().body(queryForProductsUseCase.getById(id).get());
    }
}
