package es.netmind.demoprocessor;

import es.netmind.demoprocessor.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.SendTo;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@SpringBootApplication
//@EnableBinding(Processor.class) // old way
@Slf4j
@Configuration
public class DemoProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProcessorApplication.class, args);
    }

    /*@StreamListener(Processor.INPUT) // old way
    @SendTo(Processor.OUTPUT)
    public String process(String text) {
        log.info("Processing: " + text);
        return text.toUpperCase();
    }*/

    /*@StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Flux<String> receive(Flux<String> stream) {
        return stream.map(text -> "[[" + text + "]]");
    }*/

    /**
     * functional approach
     **/
    @Bean
    public Function<Message, Message> convertToUppercase() {
        return (aMess) -> {
            log.info("Received {}", aMess);
            aMess.setTitle(aMess.getTitle().toUpperCase());
            aMess.setBody(aMess.getBody().toUpperCase());
            log.info("Sending {}", aMess);
            return aMess;
        };
    }

    /**
     * functional reactive approach
     **/
    /*@Bean
    Function<Flux<Message>, Flux<Message>> processIt() {
        return payloads -> payloads.map(message -> {
            log.info("Received reactive {}", message);
            message.setTitle(message.getTitle().toUpperCase());
            message.setBody(message.getBody().toUpperCase());
            log.info("Sending reactive {}", message);
            return message;
        });
    }*/
}