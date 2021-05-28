package com.spring_boot_backend.ServicesTests;

import com.spring_boot_backend.product.adapter.out.persistence.ProductRepositoryOut;
import com.spring_boot_backend.product.application.ports.in.CreateProductUseCase;
import com.spring_boot_backend.product.application.services.CreateProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class CreateProductServiceTests {

    @Autowired
    private CreateProductUseCase createProductUseCase;

    private Set<String> listMarkets = new HashSet<>();
    private Set<String> listTechnologies = new HashSet<>();

    public CreateProductServiceTests(){
        this.listMarkets.add("Music listener market");
        this.listTechnologies.add("Java");
    }
    @Test
    void testCreatingAProductInTheDataBaseUsingService(){
        createProductUseCase.createProduct("Service Test", "Description of service test", this.listMarkets, this.listTechnologies);
    }
}
