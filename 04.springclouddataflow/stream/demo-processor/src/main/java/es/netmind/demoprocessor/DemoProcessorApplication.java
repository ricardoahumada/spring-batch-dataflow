package es.netmind.demoprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableBinding(Processor.class)
public class DemoProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoProcessorApplication.class, args);
    }

    @StreamListener
    @Output(Processor.OUTPUT)
    public Flux<String> receive(@Input(Processor.INPUT) Flux<String> stream) {
        return stream.map(text -> "[[" + text + "]]");
    }

}
