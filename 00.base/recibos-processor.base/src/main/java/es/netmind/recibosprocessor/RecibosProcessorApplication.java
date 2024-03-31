package es.netmind.recibosprocessor;

import es.netmind.recibosprocessor.model.Errores;
import es.netmind.recibosprocessor.model.Recibo;
import es.netmind.recibosprocessor.util.CSVUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Set;
import java.util.function.Function;

@SpringBootApplication
@Slf4j
@Configuration
public class RecibosProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecibosProcessorApplication.class, args);
    }

    
}
