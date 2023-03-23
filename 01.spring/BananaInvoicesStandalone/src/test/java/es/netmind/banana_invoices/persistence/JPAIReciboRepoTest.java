package es.netmind.banana_invoices.persistence;

import es.netmind.banana_invoices.config.SpringConfig;
import es.netmind.banana_invoices.models.Propietario;
import es.netmind.banana_invoices.models.Recibo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class JPAIReciboRepoTest {

    @Autowired
    IReciboRepo reciboRepo;

    @Test
    void findAll() {
        try {
            List<Recibo> recibos = reciboRepo.findAll();
            assertNotNull(recibos);
            System.out.println("****recibos:" + recibos);
        } catch (Exception e) {
            fail("Error:" + e.getMessage());
        }

    }

    @Test
    public void savePositive() {
        try {
            Propietario prop = new Propietario("prop","p@p.com","66666666",34);
            Recibo rec = new Recibo(prop, LocalDate.now(),"Lapiz",200,0.9f,180d,15f,207d,true,true);
            reciboRepo.save(rec);
            assertTrue(rec.getId() > 0);
            System.out.println("****Recibo:"+rec);
        } catch (Exception e) {
            fail("Error:" + e.getMessage());
        }

    }

    @Test
    void findById() {
        try {
            Recibo rec = reciboRepo.findById(1L);
            assertNotNull(rec);
            System.out.println("****recibo:" + rec);
        } catch (Exception e) {
            fail("Error:" + e.getMessage());
        }
    }
}