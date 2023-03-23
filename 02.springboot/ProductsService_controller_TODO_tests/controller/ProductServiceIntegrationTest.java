package com.netmind.productsservice.controller;

import com.netmind.productsservice.model.Product;
import com.netmind.productsservice.persistence.ProductsRepository;
import com.netmind.productsservice.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

[TODO EXTENDSWITH]
public class ProductServiceIntegrationTest {

    [TODO TestConfiguration]
    static class ProductServiceImplTestContextConfiguration {
        [TODO Bean]
        public ProductsService productsService() {
            return new ProductsService();
        }

        [TODO Bean]
        public ProductServiceController productsServiceController() {
            return new ProductServiceController();
        }
    }

    [TODO BeforeEach]
    public void setUp() {
        List<Product> products = Arrays.asList(
                new Product(1L, "Fake product", "")
        );

        [TODO WHEN REPO findByNameContaining]
                .thenReturn(products);

        [TODO WHEN REPO findAll]
        Mockito.when(productsRepository.findAll())
                .thenReturn(products);
    }

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductServiceController controller;
    @MockBean
    private ProductsRepository productsRepository;

    @Test
    public void whenValidText_thenProductsShouldBeFound() {
        String text = "Fake";
        List<Product> found = productsService.getProductsByText(text);
        assertThat(found).[TODO isNotEmpty];
        assertThat(found.get(0).getName()).[TODO contains text];

    }

    @Test
    void givenProducts_whengetAllProducts_thenIsNotNull() {
        ResponseEntity<List<Product>> products = controller.getAllProducts();

        assertThat(products.getStatusCode().value())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(products.getBody())
                .isNotNull();
    }

}