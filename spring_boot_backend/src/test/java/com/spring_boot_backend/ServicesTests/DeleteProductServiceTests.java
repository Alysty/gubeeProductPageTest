package com.spring_boot_backend.ServicesTests;

import com.spring_boot_backend.product.adapter.in.stub.ProductRepositoryMemory;
import com.spring_boot_backend.product.application.ports.in.ProductRepository;
import com.spring_boot_backend.product.application.ports.out.DeleteProductUseCase;
import com.spring_boot_backend.product.application.services.DeleteProductService;
import com.spring_boot_backend.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class DeleteProductServiceTests {

    private DeleteProductUseCase deleteProductUseCase;

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
        deleteProductUseCase = new DeleteProductService(productRepository);
    }

    @Test
    public void shouldCreateAProductInTheDatabaseThenDeleteItAndCHeckIfItWasActuallyDeleted(){
        //given
        String id;
        Product product = Product.builder()
                .productName("Library")
                .description("this is a library")
                .targetMarketStack(listMarkets)
                .technologiesStack(listTechnologies)
                .build();
        productRepository.save(product);

        var productList = productRepository.findAllByProductNameLike( ProductRepository.ProductFilter
                .builder()
                .name("Library")
                .technologiesStack(new HashSet<>())
                .targetMarketStack(new HashSet<>())
                .build()
        );
        id = productList.get(0).getId();
        //when
        deleteProductUseCase.deleteProduct(id);
        //then
        Assertions.assertEquals(0, productRepository.findAllByProductNameLike( ProductRepository.ProductFilter
                .builder()
                .name("Library")
                .technologiesStack(new HashSet<>())
                .targetMarketStack(new HashSet<>())
                .build()
        ).size());
    }


}
