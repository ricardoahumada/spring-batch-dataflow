package es.netmind.demosource;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DemoSourceApplication.class)
@AutoConfigureMockMvc
//@ActiveProfiles("dev")
@Slf4j
class DemoSourceApplicationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void when_send_get_isOk() {
        try {
            // given:
            // when:
            //then:
            MvcResult result = mvc.perform(get("/")
                            .param("text","Hello new!")
                            .accept(MediaType.TEXT_PLAIN)
                    )
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andReturn();

            String contentAsString = result.getResponse().getContentAsString();
//            AuthResponse response = new ObjectMapper().readValue(contentAsString, AuthResponse.class);

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
