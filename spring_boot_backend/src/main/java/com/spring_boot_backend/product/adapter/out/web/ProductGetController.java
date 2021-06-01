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

    // Mapping for getting all the possible searches for products in the database
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getRequestForAllSearches(@RequestParam(name = "name", required = false) String name,
                                                 @RequestParam(name = "markets", required = false) String[] markets,
                                                 @RequestParam(name = "technologies", required = false) String[] technologies)
    {

        if(name==null){
            name = "";
        }
        if(name==""){
            List<Product> productList = queryForProductsService.searchForProductsWithNameOnly("");
            return ResponseEntity.ok().body(productList);
        }
        if(markets == null && technologies == null){
            List<Product> productList = queryForProductsService.searchForProductsWithNameOnly(name);
            return ResponseEntity.ok().body(productList);
        }
        if(markets == null){
            List<Product> productList = queryForProductsService
                    .searchForProductsWithoutUsingMarkets(name, new HashSet<>(Arrays.asList(technologies)));
            return ResponseEntity.ok().body(productList);
        }
        if (technologies == null){
            List<Product> productList = queryForProductsService
                    .searchForProductsWithoutUsingTechnologies(name, new HashSet<>(Arrays.asList(markets)));
            System.out.println(productList);
            return ResponseEntity.ok().body(productList);
        }
        List<Product> productList = queryForProductsService
                .searchForProductsUsingAllParameters(name, new HashSet<>(Arrays.asList(markets)), new HashSet<>(Arrays.asList(technologies)));
        return ResponseEntity.ok().body(productList);

    }

}
