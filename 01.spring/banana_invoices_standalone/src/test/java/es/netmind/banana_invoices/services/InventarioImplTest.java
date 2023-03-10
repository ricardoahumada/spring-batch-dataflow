package es.netmind.banana_invoices.services;

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
@ContextConfiguration(classes={SpringConfig.class})
class InventarioImplTest {

    @Autowired
    IInventario inventario;

//    @Test
    public void findAllPositive() {

        List<Propietario> propietarios = inventario.findAll();
        assertNotNull(propietarios);
        System.out.println("Propietarios:" + propietarios);

    }
}