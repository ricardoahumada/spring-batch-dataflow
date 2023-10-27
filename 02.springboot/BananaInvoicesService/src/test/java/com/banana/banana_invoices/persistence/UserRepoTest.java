package com.banana.banana_invoices.persistence;

import com.banana.banana_invoices.models.ERole;
import com.banana.banana_invoices.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = {"com.banana.banana_invoices.persistence"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ActiveProfiles("dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepoTest {
    static final Logger logger = LoggerFactory.getLogger(UserRepoTest.class);

    @Autowired
    UserRepository userRepo;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @BeforeAll
    @Commit // force REAL saving in DB
    public void clean() {
        try {
            logger.info("**** cleaning users...");
            userRepo.deleteUsersByEmail("u@u.com");
            userRepo.deleteUsersByEmail("a@a.com");
            userRepo.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Commit // force REAL saving in DB
    public void savePositive_RoleUser() {

        try {
            User newUser = new User(null, "u@u.com", passwordEncoder.encode("pa55wrd"), ERole.USER);
            userRepo.save(newUser);
            logger.info("****Usuario:" + newUser);

            assertTrue(newUser.getId() > 0);
        } catch (Exception e) {
            fail("Error:" + e.getMessage());
        }

    }

    @Test
    @Commit
    public void savePositive_RoleAdmin() {
        try {
            User newUser = new User(null, "a@a.com", passwordEncoder.encode("pa55wrd"), ERole.ADMIN);
            userRepo.save(newUser);
            logger.info("****Usuario:" + newUser);

            assertTrue(newUser.getId() > 0);
        } catch (Exception e) {
            fail("Error:" + e.getMessage());
        }
    }

    @Test
    public void findAllPositive() {
        try {
            List<User> usuarios = userRepo.findAll();
            logger.info("****Users:" + usuarios);
            assertNotNull(usuarios);
        } catch (Exception e) {
            fail("Error:" + e.getMessage());
        }

    }
}