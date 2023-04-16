package es.netmind.demosource;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DemoSourceApplication.class)
@AutoConfigureMockMvc
//@ActiveProfiles("dev")
@Import({TestChannelBinderConfiguration.class})
@Slf4j
class DemoSourceApplicationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private InputDestination input;

    @Autowired
    private OutputDestination output;

    @Test
    void when_send_get_then_isOk() {
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
