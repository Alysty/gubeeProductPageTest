package com.spring_boot_backend.ServicesTests;

import com.spring_boot_backend.product.adapter.in.stub.ProductRepositoryMemory;
import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.ports.out.UpdateProductUseCase;
import com.spring_boot_backend.product.application.services.UpdateProductService;
import com.spring_boot_backend.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

public class UpdateProductServiceTests {


    private UpdateProductUseCase updateProductUseCase;

    private ProductRepository productRepository;

    private Set<String> listMarkets1 = new HashSet<>();
    private Set<String> listTechnologies1 = new HashSet<>();

    private Set<String> listMarkets2 = new HashSet<>();
    private Set<String> listTechnologies2 = new HashSet<>();
    @BeforeEach
    public void init(){
        this.listMarkets1.add("Retailers");
        this.listTechnologies1.add("Java");
        this.listMarkets2.add("Music listener market");
        this.listMarkets2.add("Retailers");
        this.listTechnologies2.add("Java");
        this.listTechnologies2.add("Angular");
        productRepository = new ProductRepositoryMemory();
        updateProductUseCase = new UpdateProductService(productRepository);
    }
    @Test
    public void shouldCreateAProductInDatabaseAndUpdateItCheckingIfTheProductWasACtuallyUpdated(){
        //given
        String id;
        Product product = Product.builder()
                .productName("Library")
                .description("this is a library")
                .targetMarketStack(listMarkets1)
                .technologiesStack(listTechnologies1)
                .build();

        productRepository.save(product);

        var productList =  productRepository.findAllByFiltering(ProductRepository.ProductFilter
                .builder()
                .name("Library")
                .technologiesStack(listTechnologies1)
                .targetMarketStack(listMarkets1)
                .build()
        );
        id = productList.get(0).getId();
        //when
        updateProductUseCase.updateProduct(
                UpdateProductUseCase.UpdateProductRequest
                        .builder()
                        .id(id)
                        .productName("Test")
                        .description("Test")
                        .targetMarketStack(listMarkets2)
                        .technologiesStack(listTechnologies2)
                        .build()
        );

        //then
        productRepository.findAllByFiltering(
                ProductRepository
                        .ProductFilter
                        .builder()
                        .name("Test")
                        .targetMarketStack(listMarkets2)
                        .technologiesStack(listTechnologies2)
                        .build()
        ).forEach(product1 -> System.out.println(product1.toString()));

        Assertions.assertEquals(1,
                productRepository.findAllByFiltering(
                        ProductRepository
                                .ProductFilter
                                .builder()
                                .name("Test")
                                .targetMarketStack(listMarkets2)
                                .technologiesStack(listTechnologies2)
                                .build()
                ).size()
        );
    }

}
