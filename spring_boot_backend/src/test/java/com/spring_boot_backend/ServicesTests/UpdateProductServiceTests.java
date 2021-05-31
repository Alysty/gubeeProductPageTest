package com.spring_boot_backend.ServicesTests;

import com.spring_boot_backend.product.adapter.in.persistence.ProductRepositoryIn;
import com.spring_boot_backend.product.application.services.UpdateProductService;
import com.spring_boot_backend.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class UpdateProductServiceTests {
    @Autowired
    UpdateProductService updateProductService;

    @Autowired
    ProductRepositoryIn productRepositoryIn;

    private Set<String> listMarkets = new HashSet<>();
    private Set<String> listTechnologies = new HashSet<>();

    public UpdateProductServiceTests() {
        this.listMarkets.add("Music listener market updated");
        this.listTechnologies.add("Java updated");
    }
    @Test
    public void testToUpdateAProductInTheDatabase(){
        Product product = (Product) productRepositoryIn.findAll().toArray()[0];
        updateProductService.updateProduct(product.getId(), "This was Updated", "This was Updated", listMarkets, listTechnologies);
        Assertions.assertTrue(productRepositoryIn.findAllByProductNameLike("This was Updated").toArray().length>0);
    }

}
