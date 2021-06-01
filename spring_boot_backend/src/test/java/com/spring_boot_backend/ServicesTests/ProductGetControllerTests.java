package com.spring_boot_backend.ServicesTests;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring_boot_backend.product.application.services.QueryForProductsService;
import com.spring_boot_backend.product.domain.Product;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@WebMvcTest
public class ProductGetControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QueryForProductsService queryForProductsService;

    private static ObjectMapper mapper = new ObjectMapper();

    private Set<String> listMarkets1 = new HashSet<>();

    private Set<String> listTechnologies1 = new HashSet<>();


    public ProductGetControllerTests(){
        this.listMarkets1.add("Music listener market");
        this.listMarkets1.add("Retailers");
        this.listTechnologies1.add("Java");
        this.listTechnologies1.add("Angular");
    }

    @Test
    void testGettingAllProductsWithControllersAndWithMockedData() throws Exception {
        List<Product> products = new ArrayList<>();

        products.add(new Product("id", "Test Name", "description",this.listMarkets1, this.listTechnologies1));


        Mockito.when(queryForProductsService.searchForProductsWithNameOnly("")).thenReturn(products);


        mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("id")))
                .andExpect(jsonPath("$[0].productName", Matchers.equalTo("Test Name")));
    }
    @Test
    void testGettingProductsWithControllersAndWithMockedDataAndWithNameAsParameterSearch() throws Exception {
        List<Product> products = new ArrayList<>();

        products.add(new Product("id", "Test Name", "description",this.listMarkets1, this.listTechnologies1));


        Mockito.when(queryForProductsService.searchForProductsWithNameOnly("Test Name")).thenReturn(products);

        mockMvc.perform(get("/products?name=Test Name")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("id")))
                .andExpect(jsonPath("$[0].productName", Matchers.equalTo("Test Name")));
    }
    @Test
    void testGettingProductsWithControllersAndWithMockedDataAndWithNameAndMarketAsParameterSearch() throws Exception {
        List<Product> products = new ArrayList<>();

        products.add(new Product("id", "Test Name", "description",this.listMarkets1, this.listTechnologies1));

        Mockito.when(queryForProductsService.searchForProductsWithoutUsingTechnologies("Test Name", this.listMarkets1)).thenReturn(products);

        mockMvc.perform(get("/products?name=Test Name&markets=Music listener market, Retailers"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("id")))
                .andExpect(jsonPath("$[0].productName", Matchers.equalTo("Test Name")));
    }
    @Test
    void testGettingProductsWithControllersAndWithMockedDataAndWithNameAndTechnologiesAsParameterSearch() throws Exception {
        List<Product> products = new ArrayList<>();

        products.add(new Product("id", "Test Name", "description",this.listMarkets1, this.listTechnologies1));

        Mockito.when(queryForProductsService.searchForProductsWithoutUsingMarkets("Test Name", this.listTechnologies1)).thenReturn(products);

        mockMvc.perform(get("/products?name=Test Name&technologies=Java, Angular"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("id")))
                .andExpect(jsonPath("$[0].productName", Matchers.equalTo("Test Name")));
    }
    @Test
    void testGettingProductsWithControllersAndWithMockedDataAndWithAllAsParameterSearch() throws Exception {
        List<Product> products = new ArrayList<>();

        products.add(new Product("id", "Test Name", "description",this.listMarkets1, this.listTechnologies1));

        Mockito.when(queryForProductsService.searchForProductsUsingAllParameters("Test Name", this.listMarkets1, this.listTechnologies1)).thenReturn(products);

        mockMvc.perform(get("/products?name=Test Name&technologies=Java, Angular&markets=Music listener market, Retailers"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.equalTo("id")))
                .andExpect(jsonPath("$[0].productName", Matchers.equalTo("Test Name")));
    }
}
