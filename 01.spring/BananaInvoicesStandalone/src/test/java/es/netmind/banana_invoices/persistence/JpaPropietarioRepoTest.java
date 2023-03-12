package es.netmind.banana_invoices.persistence;

import es.netmind.banana_invoices.config.SpringConfig;
import es.netmind.banana_invoices.models.Propietario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class JpaPropietarioRepoTest {

    @Autowired
    IPropietarioRepo propietarioRepo;

    @Test
    public void findAllPositive() {
        try {
            List<Propietario> propietarios = propietarioRepo.findAll();
            assertNotNull(propietarios);
            System.out.println(propietarios);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

    }
}