package es.netmind.demoprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.netmind.demoprocessor.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.MessageBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DemoProcessorApplication.class)
@Slf4j
@Import({TestChannelBinderConfiguration.class})
class DemoProcessorApplicationTests {

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private InputDestination inputDestination;
    @Autowired
    private OutputDestination outputDestination;

    @Test
    void when_send_message_then_ok() {
        Message aMessage = new Message("A title", "A body to transform");
//        streamBridge.send("my-src", aMessage);

        org.springframework.messaging.Message<Message> inputMessage = MessageBuilder.withPayload(aMessage).build();
        inputDestination.send(inputMessage, "my-src");

        org.springframework.messaging.Message<byte[]> outputMessage = outputDestination.receive( 10000, "my-proc");

        log.info("Received message in test {}",outputMessage);

        assertThat(outputMessage).isNotNull();
    }

}
