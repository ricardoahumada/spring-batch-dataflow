package com.banana.productsservice.controller;

import com.banana.productsservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

[TODO SPRINGBOOT]
class ProductServiceControllerTest {
    [TODO Logger];

    @Autowired
    private ProductServiceController controller;

    @Test
    void givenProducts_whengetAllProducts_thenIsNotNull() {
        [TODO] products = controller.getAllProducts();

        assertThat([TODO STATUS CODE])
                .isEqualTo([TODO STATUS CODE]);

        assertThat([TODO BODY])
                .isNotNull();
    }

    @Test
    void givenProducts_whenVaildCreateProduct_thenIsCreatedAndHaveId() {
        Product newProduct = new Product(null, "Nuevo producto", "123-123-1234");

        ResponseEntity<Product> response = controller.createProduct(newProduct);

        [TODO ASSERT STATUS CODE CREATED]
        [TODO ASSERT BODY ID > 0 ]

    }
}