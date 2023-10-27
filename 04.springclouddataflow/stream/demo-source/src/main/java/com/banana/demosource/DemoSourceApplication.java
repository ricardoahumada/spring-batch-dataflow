package com.banana.demosource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@RequestMapping("/")
@EnableBinding(Source.class)
@Slf4j
public class DemoSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSourceApplication.class, args);
    }

    @Autowired
    Source source;

    @GetMapping("")
    void send(@RequestParam String text) {
        log.info("Recived text: "+text);
        source.output().send(MessageBuilder.withPayload(text).build());
    }

    /*@Bean
    @InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
    MessageSource<String> source() {
        return (() -> MessageBuilder.withPayload("Hi").build());
    }*/

}
