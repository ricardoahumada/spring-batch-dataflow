package com.netmind.productsservice.persistence;

import com.netmind.productsservice.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest()
@ComponentScan(basePackages = {"com.netmind.productsservice.persistence"})
@AutoConfigureTestEntityManager
//@ActiveProfiles("dev")
class JPAProductsRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(JPAProductsRepositoryTest.class);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IProductsRepository jpaRepo;

    @Test
    void findAll() {
        // given
        Product aProduct = new Product(null, "Fake Product");
        entityManager.persist(aProduct);
        entityManager.flush();

        // when
        List<Product> prods = jpaRepo.findAll();
        logger.info("Prods:" + prods);

        // then
        assertThat(prods.size())
                .isGreaterThan(0);

        assertNotNull(prods);
    }

    //    @Test
    void findById() {
    }

    //    @Test
    void findByNameContaining() {
    }

    //    @Test
    void findByName() {
    }

    @Test
    void save() {
        // given
        Product aProduct = new Product(null, "Another Fake Product");

        // when
        jpaRepo.save(aProduct);

        logger.info("aProduct:" + aProduct);

        // then
        assertThat(aProduct.getId()).isGreaterThan(0);
    }

    @Test
    void deleteById() {
    }
}