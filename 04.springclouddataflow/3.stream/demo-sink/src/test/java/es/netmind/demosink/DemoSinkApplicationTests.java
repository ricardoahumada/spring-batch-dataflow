package es.netmind.demosink;

import es.netmind.demosink.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Import;

import java.util.function.Consumer;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DemoSinkApplication.class)
@Import({TestChannelBinderConfiguration.class})
@Slf4j
class DemoSinkApplicationTests {

    @Autowired
    Consumer<Message> consumer;

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private InputDestination inputDestination;
    @Autowired
    private OutputDestination outputDestination;

    @Test
    void when_send_message_then_ok() {
        Message aMessage = new Message("A title", "A body...");
        streamBridge.send("demo-strm", aMessage);

        org.springframework.messaging.Message<byte[]> outputMessage = outputDestination.receive(10000, "demo-strm");

//        log.info("Received message in test {}", outputMessage);

//        assertThat(outputMessage).isNotNull();
    }

}
