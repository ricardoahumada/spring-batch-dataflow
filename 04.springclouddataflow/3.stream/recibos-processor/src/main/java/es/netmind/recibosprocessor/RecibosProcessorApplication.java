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

    @Bean
    public Function<String, Object> validateRecibo() {
        return (aMess) -> {
            log.info("validateRecibo::received: {}", aMess);
            try {
                Recibo rec = CSVUtils.converCSVLineToRecibo(aMess);
                // System.out.println(rec);
                Set errores = rec.esValido();
                log.info("validateRecibo::esValido: {}", errores);

                if (errores.size() > 0) {
                    Errores errs = new Errores(errores);
                    log.info("validateRecibo::Sending errors {}", errs);
                    return errs;
                } else {
                    rec.setEstado(false);
                    rec.setValido(true);
                    log.info("validateRecibo::Sending recibo {}", rec);
                    return rec;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        };
    }
}
