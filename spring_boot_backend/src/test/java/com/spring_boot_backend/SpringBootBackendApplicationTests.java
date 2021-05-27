package com.spring_boot_backend;

import com.spring_boot_backend.product.adapter.out.persistence.ProductRepositoryOut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBackendApplicationTests {

    @Autowired
    private ProductRepositoryOut productRepositoryOut;


    @Test
    void contextLoads() {

    }

}
