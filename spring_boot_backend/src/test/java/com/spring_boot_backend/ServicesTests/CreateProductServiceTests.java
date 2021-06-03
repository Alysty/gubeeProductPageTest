package com.spring_boot_backend.ServicesTests;

import com.spring_boot_backend.product.adapter.in.stub.ProductRepositoryMemory;
import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.ports.out.CreateProductUseCase;
import com.spring_boot_backend.product.application.services.CreateProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class CreateProductServiceTests {

    private CreateProductUseCase createProductUseCase;

    private ProductRepository productRepository;

    private Set<String> listMarkets = new HashSet<>();
    private Set<String> listTechnologies = new HashSet<>();

    @BeforeEach
    public void init(){
        this.listMarkets.add("Music listener market");
        this.listMarkets.add("Retailers");
        this.listTechnologies.add("Java");
        this.listTechnologies.add("Angular");
        productRepository = new ProductRepositoryMemory();
        createProductUseCase = new CreateProductService(productRepository);
    }

    @Test
    void shouldCreateAProductInTheDataBaseUsingServiceThenRetrieveItInTheDatabase(){
        //when
        createProductUseCase.createProduct(CreateProductUseCase.CreateProductRequest.builder()
                .productName("Service Test JUnit")
                .description("Description of service test")
                .targetMarketStack(this.listMarkets)
                .technologiesStack(this.listTechnologies)
                .build()
        );
        //then
        Assertions.assertTrue(
                productRepository.findAllByProductNameLike( ProductRepository.ProductFilter
                        .builder()
                        .name("Service Test JUnit")
                        .technologiesStack(this.listTechnologies)
                        .targetMarketStack(this.listMarkets)
                        .build()
                ).stream().allMatch(product -> product.getProductName().equals("Service Test JUnit"))
        );
    }
}
