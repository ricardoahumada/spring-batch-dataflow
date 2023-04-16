package es.netmind.demosink;

import es.netmind.demosink.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Payloads;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Consumer;

@SpringBootApplication
//@EnableBinding(Sink.class) //old way
@Slf4j
@Configuration
public class DemoSinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSinkApplication.class, args);
    }


    /* @StreamListener(Sink.INPUT) // old way
     void receive(@Payloads String message) {
         log.info("Received message: " + message);
         try (PrintWriter out = new PrintWriter("DemoSinkApplication.txt")) {
             out.println(message);
         } catch (FileNotFoundException e) {
             log.error(e.getMessage());
             throw new RuntimeException(e);
         }
     }
 */

    @Bean
    public Consumer<Message> onReceive() {
        return (message) -> {
            log.info("Received the value {} in Consumer", message);
            try (PrintWriter out = new PrintWriter("DemoSinkApplication.txt")) {
                out.println(message);
            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        };
    }

}
