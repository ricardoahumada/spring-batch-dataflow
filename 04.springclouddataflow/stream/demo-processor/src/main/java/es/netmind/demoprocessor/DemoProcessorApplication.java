package es.netmind.demoprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableBinding(Processor.class)
@Slf4j
public class DemoProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProcessorApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public String process(String text) {
        log.info("Processing: "+ text);
        return s.toUpperCase();
    }

    /*@StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Flux<String> receive(Flux<String> stream) {
        return stream.map(text -> "[[" + text + "]]");
    }*/
}