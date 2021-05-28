package com.spring_boot_backend.DbTests;

import com.spring_boot_backend.product.adapter.in.persistence.ProductRepositoryIn;
import com.spring_boot_backend.product.adapter.out.persistence.ProductRepositoryOut;
import com.spring_boot_backend.product.domain.Product;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersistenceMockedTests {

    @Autowired
    private ProductRepositoryOut productRepositoryOut;
    @Autowired
    private ProductRepositoryIn productRepositoryIn;

    // List of markets and technologies created as a attribute for use in multiple methods
    private Set<String> listMarkets1 = new HashSet<>();
    private Set<String> listMarkets2 = new HashSet<>();
    private Set<String> listMarkets3 = new HashSet<>();

    private Set<String> listTechnologies1 = new HashSet<>();
    private Set<String> listTechnologies2 = new HashSet<>();
    private Set<String> listTechnologies3 = new HashSet<>();

    public PersistenceMockedTests(){
        this.listMarkets1.add("Music listener market");
        this.listMarkets1.add("Retailers");
        this.listMarkets2.add("Retailers");

        this.listTechnologies1.add("Java");
        this.listTechnologies1.add("Angular");
        this.listTechnologies2.add("Java");
    }
    @Test
    @Order(1)
    void TestInserting3ProductsIntoTheDataBase(){

        this.productRepositoryOut.deleteAll();



        Product product1 = new Product("Cd seller manager",
                "A software that manages a cd seller's store",
                this.listMarkets1,
                this.listTechnologies1);
        Product product2 = new Product("Library manager",
                "A software that manages a library",
                this.listMarkets2,
                this.listTechnologies2);
        Product product3 = new Product("Test no markets or technologies",
                "this is a test",
                this.listMarkets3,
                this.listTechnologies3);


        this.productRepositoryOut.saveAll(Arrays.asList(product1,product2,product3));

    }

    //Testing getting all products stored in the database
    @Test
    @Order(2)
    void testGettingAllProductsFromDatabase(){
        Assertions.assertTrue(this.productRepositoryIn.findAll().toArray().length == 3);
    }
    @Test
    @Order(3)
    void testQueryingWithAllWithoutNameWhereYouGetTwoReturns(){
        List<Product> products = this.productRepositoryIn.
                findAllByProductNameLikeAndTargetMarketStackInAndTechnologiesStackIn(
                        "", this.listMarkets2, this.listTechnologies2);
        Assertions.assertTrue(products.toArray().length == 2);
    }
    @Test
    @Order(4)
    void testQueryingWithAllParametersUsedWhereYouGetASingleReturn(){
        List<Product> products = this.productRepositoryIn.
                findAllByProductNameLikeAndTargetMarketStackInAndTechnologiesStackIn(
                        "Cd seller manager", this.listMarkets2, this.listTechnologies2);
        Assertions.assertTrue(products.toArray().length == 1);
    }
    @Test
    @Order(5)
    void testQueryingWithoutMarketParameters(){
        List<Product> products =this.productRepositoryIn.
                findAllByProductNameLikeAndTargetMarketStackIn("", this.listMarkets2);

        Assertions.assertTrue(products.toArray().length == 2);
    }
    @Test
    @Order(6)
    void testQueryingWithoutTechnologiesParameters(){
        List<Product> products = this.productRepositoryIn.
                findAllByProductNameLikeAndTechnologiesStackIn("",this.listTechnologies2);

        Assertions.assertTrue(products.toArray().length == 2);
    }

}
