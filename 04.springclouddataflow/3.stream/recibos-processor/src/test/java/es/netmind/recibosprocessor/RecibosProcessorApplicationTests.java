package es.netmind.recibosprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import es.netmind.recibosprocessor.model.Errores;
import es.netmind.recibosprocessor.model.Recibo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@Slf4j
@Import({TestChannelBinderConfiguration.class})
class RecibosProcessorApplicationTests {
    @Autowired
    private InputDestination inputDestination;
    @Autowired
    private OutputDestination outputDestination;

    @Test
    void when_recived_valid_string_then_sent_recibo() {
        String aMessage = "6,Room 1296,PO Box 7459,2023-03-22,2023-10-16,Chickie Lewins,26,Paper Cocktail Umberlla 80 - 180,15,83,1245,21,1506.45";

        org.springframework.messaging.Message<String> inputMessage = MessageBuilder.withPayload(aMessage).build();
        inputDestination.send(inputMessage, "my-src");

        org.springframework.messaging.Message<byte[]> outputMessage = outputDestination.receive(10000, "my-proc");

        log.info("Received message in test {}", outputMessage.getPayload());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Recibo recRecibo = null;
        try {
            recRecibo = objectMapper.readValue(outputMessage.getPayload(), Recibo.class);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        log.info("Received message object in test {}", recRecibo);

        assertThat(outputMessage).isNotNull();
        assertThat(recRecibo.getTotal()).isGreaterThan(0);
    }

    @Test
    void when_recived_invalid_string_then_sent_errores() {
        String aMessage = "1,PO Box 93683,Apt 1344,2022-06-23,2022-05-20,Quinlan Impy,41,Pickerel - Fillets,79,54,,7,4564.62";

        org.springframework.messaging.Message<String> inputMessage = MessageBuilder.withPayload(aMessage).build();
        inputDestination.send(inputMessage, "my-src");

        org.springframework.messaging.Message<byte[]> outputMessage = outputDestination.receive(10000, "my-proc");

        log.info("Received message in test {}", outputMessage.getPayload());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Errores errores = null;
        try {
            errores = objectMapper.readValue(outputMessage.getPayload(), Errores.class);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        log.info("Received message object in test {}", errores);

        assertThat(outputMessage).isNotNull();
        assertThat(errores.getLista_errores().size()).isGreaterThan(0);
    }

}
