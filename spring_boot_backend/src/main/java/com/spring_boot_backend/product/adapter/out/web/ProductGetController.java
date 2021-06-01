package com.spring_boot_backend.product.adapter.out.web;

import com.spring_boot_backend.product.application.services.QueryForProductsService;
import com.spring_boot_backend.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/products")
public class ProductGetController {

    QueryForProductsService queryForProductsService;
    public ProductGetController(QueryForProductsService queryForProductsService){
        Objects.requireNonNull(queryForProductsService);
        this.queryForProductsService = queryForProductsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAll(){
        List<Product> productList = queryForProductsService.searchForProductsWithNameOnly("");
        return ResponseEntity.ok().body(productList);

    }
}
