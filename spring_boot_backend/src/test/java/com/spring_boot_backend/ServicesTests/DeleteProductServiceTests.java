package com.spring_boot_backend.ServicesTests;

import com.spring_boot_backend.product.adapter.in.persistence.ProductRepositoryIn;
import com.spring_boot_backend.product.application.services.DeleteProductService;
import com.spring_boot_backend.product.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteProductServiceTests {

    @Autowired
    DeleteProductService deleteProductService;

    @Autowired
    ProductRepositoryIn productRepositoryIn;

    @Test
    public void testToDeleteAProductInTheDatabase(){
        Product product = (Product) productRepositoryIn.findAll().toArray()[0];
        String productName = product.getProductName();
        deleteProductService.deleteProduct(product.getId());
        Assertions.assertTrue(productRepositoryIn.findAllByProductNameLike(productName).toArray().length==0);
    }
}
