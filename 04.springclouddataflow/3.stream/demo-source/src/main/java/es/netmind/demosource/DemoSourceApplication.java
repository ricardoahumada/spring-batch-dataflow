package es.netmind.demosource;

import es.netmind.demosource.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.UUID;
import java.util.function.Supplier;


@SpringBootApplication
//@RestController
//@RequestMapping("/")
//@EnableBinding(Source.class) // old way
@Slf4j
//@Configuration
public class DemoSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSourceApplication.class, args);
    }

    /*// @Autowired
    Source source; // old way

    @Autowired
    private StreamBridge streamBridge;

    @GetMapping("")
    void send(@RequestParam String title, @RequestParam String body) {
        log.info("Recived: " + title + "::" + body);
        Message aMessage = new Message(title, body);
        // source.output().send(MessageBuilder.withPayload(aMessage).build());// old way
        streamBridge.send(Source.OUTPUT, aMessage);
    }*/

    /** functional approaches **/
   /* @Bean
    @InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
    MessageSource<String> source() {
        return (() -> MessageBuilder.withPayload("Hi").build());
    }*/

    /*@Bean
    public Supplier<String> stringSupplier() {
        return () -> "Hello from Id: " + UUID.randomUUID();
    }*/

    /**
     * functional reactive approach
     **/
    /*@Bean
    // @PollableBean
    public Supplier<Flux<String>> stringReactSupplier() {
        return () -> Flux.just("foo", "bar", "baz");
    }*/

}
