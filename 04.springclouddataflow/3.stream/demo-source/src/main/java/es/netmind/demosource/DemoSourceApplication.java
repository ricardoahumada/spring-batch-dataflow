package es.netmind.demosource;

import es.netmind.demosource.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequestMapping("/")
//@EnableBinding(Source.class) // old way
@Slf4j
public class DemoSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSourceApplication.class, args);
    }

//    @Autowired
    Source source; // old way

    @Autowired
	private StreamBridge streamBridge;

    @GetMapping("")
    void send(@RequestParam String text) {
        log.info("Recived text: " + text);
        Message aMessage = new Message("A title", text);
//        source.output().send(MessageBuilder.withPayload(aMessage).build());// old way
        streamBridge.send(Source.OUTPUT, aMessage);
    }

   /* @Bean // functional approachv
    @InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
    MessageSource<String> source() {
        return (() -> MessageBuilder.withPayload("Hi").build());
    }*/

}
