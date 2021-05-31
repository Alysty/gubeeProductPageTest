package com.spring_boot_backend.ServicesTests;

import com.spring_boot_backend.product.application.services.QueryForProductsService;
import com.spring_boot_backend.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class QueryForProductServiceTests {

    @Autowired
    private QueryForProductsService queryForProductsService;

    private Set<String> listMarkets1 = new HashSet<>();
    private Set<String> listMarkets2 = new HashSet<>();

    private Set<String> listTechnologies1 = new HashSet<>();
    private Set<String> listTechnologies2 = new HashSet<>();

    public QueryForProductServiceTests(){
        this.listMarkets1.add("Music listener market");
        this.listMarkets1.add("Retailers");
        this.listMarkets2.add("Retailers");

        this.listTechnologies1.add("Java");
        this.listTechnologies1.add("Angular");
        this.listTechnologies2.add("Java");
    }

    //Testing getting all products stored in the database
    @Test
    void testGettingAllProductsFromDatabase(){
        Assertions.assertTrue(this.queryForProductsService.searchForProductsWithNameOnly("").toArray().length == 3);
    }
    @Test
    void testQueryingWithAllWithoutNameWhereYouGetOneReturn(){
        List<Product> products = this.queryForProductsService.searchForProductsUsingAllParameters(
                        "", this.listMarkets2, this.listTechnologies2);
        Assertions.assertTrue(products.toArray().length == 1);
    }
    @Test
    void testQueryingWithAllParametersUsedWhereYouGetASingleReturn(){
        List<Product> products = this.queryForProductsService.searchForProductsUsingAllParameters(
                        "Library manager", this.listMarkets2, this.listTechnologies2);
        Assertions.assertTrue(products.toArray().length == 1);
    }
    @Test
    void testQueryingWithoutMarketParameters(){
        List<Product> products =this.queryForProductsService.searchForProductsWithoutUsingMarkets("", this.listTechnologies2);
        Assertions.assertTrue(products.toArray().length == 2);
    }
    @Test
    void testQueryingWithoutTechnologiesParameters(){
        List<Product> products = this.queryForProductsService.searchForProductsWithoutUsingTechnologies("",this.listMarkets2);

        Assertions.assertTrue(products.toArray().length == 1);
    }
    @Test
    void testQueryingWithoutMarketAndTechnologiesParameters(){
        List<Product> products = this.queryForProductsService.searchForProductsWithNameOnly("Library manager");
        Assertions.assertTrue(products.toArray().length == 1);
    }

}
