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
            System.out.println("****Propietarios:"+propietarios);
        } catch (Exception e) {
            fail("Error:" + e.getMessage());
        }

    }

    @Test
    public void savePositive() {
        try {
            Propietario prop = new Propietario(null, "ricardo", "r@r.com", "1234567", 45);
            propietarioRepo.save(prop);
            assertTrue(prop.getPid() > 0);
            System.out.println("****Propietario:"+prop);
        } catch (Exception e) {
            fail("Error:" + e.getMessage());
        }

    }
}