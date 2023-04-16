package es.netmind.demosink;

import es.netmind.demosink.model.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;

import java.util.function.Consumer;

@SpringBootTest(classes = DemoSinkApplication.class)
class DemoSinkApplicationTests {

    @Autowired
    Consumer<Message> consumer;

    @Autowired
    private StreamBridge streamBridge;

    @Test
    void when_send_message_then_ok() {
        Message aMessage = new Message("A title", "A body...");
        streamBridge.send("demo-strm", aMessage);
    }

}
