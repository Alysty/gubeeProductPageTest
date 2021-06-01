package com.spring_boot_backend.product.adapter.out.web;

import com.spring_boot_backend.product.application.services.QueryForProductsService;
import com.spring_boot_backend.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findByNameOnly(@RequestParam(name = "name") String name){
        System.out.println("GOT HERE");
        List<Product> productList = queryForProductsService.searchForProductsWithNameOnly(name);
        return ResponseEntity.ok().body(productList);
    }

}
