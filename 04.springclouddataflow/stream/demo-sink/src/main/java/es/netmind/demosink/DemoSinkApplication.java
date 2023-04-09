package es.netmind.demosink;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.Payloads;
import org.springframework.messaging.Message;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

@SpringBootApplication
@EnableBinding(Sink.class)
@Slf4j
public class DemoSinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSinkApplication.class, args);
    }


    @StreamListener(Sink.INPUT)
    void receive(@Payloads String message) {
        log.info("Received message: " + message);
        try (PrintWriter out = new PrintWriter("DemoSinkApplication.txt")) {
            out.println(message);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
