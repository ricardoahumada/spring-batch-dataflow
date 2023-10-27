package com.banana.banana_invoices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.banana.banana_invoices.BananaInvoicesServiceApplication;
import com.banana.banana_invoices.models.ERole;
import com.banana.banana_invoices.models.Recibo;
import com.banana.banana_invoices.models.User;
import com.banana.banana_invoices.persistence.IReciboRepo;
import com.banana.banana_invoices.persistence.UserRepository;
import com.banana.banana_invoices.security.AuthRequest;
import com.banana.banana_invoices.security.AuthResponse;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BananaInvoicesServiceApplication.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("prod")
class AuthServiceTest {

    Logger logger = LoggerFactory.getLogger(AuthServiceTest.class);

    String accessToken = null;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IReciboRepo reciboRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    @Commit // force REAL saving in DB
    public void clean() {
        try {
            logger.info("**** cleaning users...");
            userRepository.deleteUsersByEmail("t@t.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() {
        List<Recibo> recibos = Arrays.asList(
                new Recibo(1L, LocalDate.of(2024, 1, 8), "Silla", 10, 20, 200, 21, 242),
                new Recibo(2L, LocalDate.of(2023, 12, 3), "Lápiz", 100, 1, 100, 21, 121)
        );

        Mockito.when(reciboRepository.findAll())
                .thenReturn(recibos);

        Mockito.when(reciboRepository.save(Mockito.any(Recibo.class)))
                .thenAnswer(elem -> {
                    Recibo ap = (Recibo) elem.getArguments()[0];
                    ap.setId(100L);
                    return ap;
                });
    }

    @Test
    @Order(1)
    void given_existing_user_when_login_success() throws Exception {
        // given
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String email = "t@t.com";
        String password = "tpasswrd";
        String enc_password = passwordEncoder.encode(password);

        User aUser = new User(null, email, enc_password, ERole.USER);
        userRepository.save(aUser);

        logger.info("Saved user:" + aUser);

        // when
        AuthRequest authRequest = new AuthRequest(email, password);

        //then
        MvcResult result = mvc.perform(post("/auth/login")
                        .content(asJsonString(authRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        AuthResponse response = new ObjectMapper().readValue(contentAsString, AuthResponse.class);

        logger.info(response.toString());

        accessToken = response.getAccessToken();
    }

    @Test
    @Order(2)
    void given_accesstoken_when_getRecibos_then_success() {

        try {
            // given: existing token
            // given_existing_user_when_login_success(); // must  have executed previously in test sequence

            // when
            mvc.perform(get("/recibos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .header("Authorization", "Bearer " + accessToken)
                    )
                    // then
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$[0].id", is(1)));

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}