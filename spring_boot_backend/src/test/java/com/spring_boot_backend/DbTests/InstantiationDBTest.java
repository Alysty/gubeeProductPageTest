package com.spring_boot_backend.DbTests;

import com.spring_boot_backend.product.adapter.in.persistence.ProductRepositoryIn;
import com.spring_boot_backend.product.adapter.out.persistence.ProductRepositoryOut;
import com.spring_boot_backend.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class InstantiationDBTest {

    @Autowired
    private ProductRepositoryOut productRepositoryOut;
    @Autowired
    private ProductRepositoryIn productRepositoryIn;
    @Test
    public void TestDB(){

        productRepositoryOut.deleteAll();

        Set<String> listMarkets1 = new HashSet<>();
        listMarkets1.add("Music listener market");
        listMarkets1.add("Retailers");

        Set<String> listMarkets2 = new HashSet<>();
        listMarkets2.add("Music listener market");
        listMarkets2.add("Retailers");

        Set<String> listMarkets3 = new HashSet<>();

        Set<String> listTechnologies1 = new HashSet<>();
        listTechnologies1.add("Java");
        listTechnologies1.add("Angular");

        Set<String> listTechnologies2 = new HashSet<>();
        listTechnologies2.add("Java");

        Set<String> listTechnologies3 = new HashSet<>();
        Product product1 = new Product("Cd seller manager",
                "A software that manages a cd seller's store",
                listMarkets1,
                listTechnologies1);
        Product product2 = new Product("Library manager",
                "A software that manages a library",
                listMarkets2,
                listTechnologies2);
        Product product3 = new Product("Test no markets or technologies",
                "this is a test",
                listMarkets3,
                listTechnologies3);


        productRepositoryOut.saveAll(Arrays.asList(product1,product2,product3));

        //System.out.println("--------------TEST--------------\n" + productRepositoryIn.findAll().toArray()[0] + "\n--------------TEST--------------");
        Assertions.assertTrue(productRepositoryIn.findAll().toArray().length == 3);

    }
}
