package com.netmind.productsservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netmind.productsservice.model.Product;
import com.netmind.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.BDDMockito.given;

[TODO ExtendWith]
[TODO WebMvcTest]
public class ProductServiceControllerTest_WebMvcTest {

    [TODO]
    public void setUp() {
        List<Product> products = Arrays.asList(
                [TODO ProductS]
        );

        [TODO WHEN repository.findByNameContaining("Fake")]
                .thenReturn(products);

        Mockito.when(repository.findAll())
                .[TODO thenReturn];

        Mockito.when(repository.save(Mockito.any(Product.class)))
                .thenAnswer(elem -> {
                    [TODO]
                });
    }

    @Autowired
    private MockMvc mvc;

    [TODO]
    private ProductsRepository repository;

    @Test
    public void givenProducts_whenGetProducts_thenReturnJsonArray() throws Exception {

        Product aProduct = new Product(1L, "Fake product", "123-123-1234");

        List<Product> allProducts = Arrays.asList(aProduct);

        given(repository.findAll()).willReturn(allProducts);

        [TODO MVC PERFORM GET PRODUCTS]
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(aProduct.getName())));
    }

    @Test
    void givenProducts_whenVaildCreateProduct_thenIsCreatedAndHaveId() throws Exception {
        Product newProduct = new Product(null, "Nuevo producto", "123-123-1234");

        [TODO MVC PERFORM POST PRODUCT]
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(100)));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}