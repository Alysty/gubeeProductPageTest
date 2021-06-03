package com.spring_boot_backend.ServicesTests;


import com.spring_boot_backend.product.adapter.in.stub.ProductRepositoryMemory;
import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.services.QueryForProductsService;
import com.spring_boot_backend.product.domain.Product;
import org.junit.jupiter.api.*;

import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueryForProductServiceTests {

    private QueryForProductsService queryForProductsService;

    private ProductRepository productRepository;

    private Set<String> listMarkets1 = new HashSet<>();

    private Set<String> listTechnologies1 = new HashSet<>();

    private Set<String> listMarkets2 = new HashSet<>();

    private Set<String> listTechnologies2 = new HashSet<>();
    @BeforeEach
    public void init(){
        this.listMarkets1.add("Music listener market");
        this.listMarkets1.add("Retailers");
        this.listTechnologies1.add("Java");
        this.listTechnologies1.add("Angular");
        this.listMarkets2.add("Retailers");
        this.listTechnologies2.add("Java");
        productRepository = new ProductRepositoryMemory();
        queryForProductsService = new QueryForProductsService(productRepository);
    }
    @Test
    void shouldReturnAllProductsWithNameTest(){
        //given
        Product product1 = Product.builder()
                .productName("Test")
                .description("this is a test")
                .targetMarketStack(listMarkets1)
                .technologiesStack(listTechnologies1)
                .build();
        Product product2 = Product.builder()
                .productName("Library")
                .description("this is a library")
                .targetMarketStack(listMarkets2)
                .technologiesStack(listTechnologies2)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        //when
        List<Product> products = queryForProductsService.searchForProductsUsingAllParameters(
                ProductRepository.ProductFilter
                        .builder()
                        .name("Test")
                        .targetMarketStack(new HashSet<>())
                        .technologiesStack(new HashSet<>())
                        .build());
        //then
        Assertions.assertTrue(products.stream().allMatch(prod -> prod.getProductName().equals("Test")));
    }

    @Test
    void shouldReturnAllProducts(){
        //given
        Product product1 = Product.builder()
                .productName("Test")
                .description("this is a test")
                .targetMarketStack(listMarkets1)
                .technologiesStack(listTechnologies1)
                .build();
        Product product2 = Product.builder()
                .productName("Library")
                .description("this is a library")
                .targetMarketStack(listMarkets2)
                .technologiesStack(listTechnologies2)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        //when
        List<Product> products = queryForProductsService.searchForProductsUsingAllParameters(
                ProductRepository.ProductFilter
                        .builder()
                        .name("")
                        .targetMarketStack(new HashSet<>())
                        .technologiesStack(new HashSet<>())
                        .build());
        //then
        Assertions.assertEquals(2, products.size());
    }
    @Test
    void shouldReturnAllProductsThatContainsTheRetailersMarket(){
        //given
        Product product1 = Product.builder()
                .productName("Test")
                .description("this is a test")
                .targetMarketStack(listMarkets1)
                .technologiesStack(listTechnologies1)
                .build();
        Product product2 = Product.builder()
                .productName("Library")
                .description("this is a library")
                .targetMarketStack(listMarkets2)
                .technologiesStack(listTechnologies2)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        //when
        List<Product> products = queryForProductsService.searchForProductsUsingAllParameters(
                ProductRepository.ProductFilter
                        .builder()
                        .name("")
                        .targetMarketStack(listMarkets2)
                        .technologiesStack(new HashSet<>())
                        .build());
        //then
        Assertions.assertEquals(2, products.size());
    }
    @Test
    void shouldReturnAllProductsThatContainsTheTechnologyJava(){
        //given
        Product product1 = Product.builder()
                .productName("Test")
                .description("this is a test")
                .targetMarketStack(listMarkets1)
                .technologiesStack(listTechnologies1)
                .build();
        Product product2 = Product.builder()
                .productName("Library")
                .description("this is a library")
                .targetMarketStack(listMarkets2)
                .technologiesStack(listTechnologies2)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        //when
        List<Product> products = queryForProductsService.searchForProductsUsingAllParameters(
                ProductRepository.ProductFilter
                        .builder()
                        .name("")
                        .targetMarketStack(new HashSet<>())
                        .technologiesStack(listTechnologies2)
                        .build());
        //then
        Assertions.assertEquals(2, products.size());
    }
    @Test
    void shouldReturnAllProductsWithNameTestThatContainsTheRetailersMarketAndContainsTheTechnologyJava(){
        //given
        Product product1 = Product.builder()
                .productName("Test")
                .description("this is a test")
                .targetMarketStack(listMarkets1)
                .technologiesStack(listTechnologies1)
                .build();
        Product product2 = Product.builder()
                .productName("Library")
                .description("this is a library")
                .targetMarketStack(listMarkets2)
                .technologiesStack(listTechnologies2)
                .build();
        productRepository.save(product1);
        productRepository.save(product2);
        //when
        List<Product> products = queryForProductsService.searchForProductsUsingAllParameters(
                ProductRepository.ProductFilter
                        .builder()
                        .name("Test")
                        .targetMarketStack(listMarkets2)
                        .technologiesStack(listTechnologies2)
                        .build());
        //then
        Assertions.assertEquals(1, products.size());
    }
}
