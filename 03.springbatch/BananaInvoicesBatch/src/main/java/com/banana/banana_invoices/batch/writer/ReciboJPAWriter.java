package com.banana.banana_invoices.batch.writer;

import com.banana.banana_invoices.models.Recibo;
import com.banana.banana_invoices.models.ReciboInvalido;
import com.banana.banana_invoices.persistence.IReciboRepo;
import com.banana.banana_invoices.persistence.ReciboInvalidoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Setter @Getter
@Transactional
public class ReciboJPAWriter implements ItemWriter<Object> {
    private IReciboRepo reciboRepo;
    private ReciboInvalidoRepository invalidoRepository;

    @Override
    public void write(List<? extends Object> list) throws Exception {
        System.out.println("ReciboJPAWriter write()....:" + list.size());

        Recibo currentRecibo = null;
        ReciboInvalido currentReciboInv = null;
        for (Object item : list) {
            currentRecibo = (Recibo) item;
            currentRecibo.setId(null);
            if (!currentRecibo.isValido()) {
                currentReciboInv = new ReciboInvalido(currentRecibo,"");
                System.out.printf("\t ...writing INVALIDO: %s\n", currentReciboInv);
                invalidoRepository.save(currentReciboInv);
            } else {
                System.out.printf("\t ...writing VALIDO: %s\n", currentRecibo);
                reciboRepo.save(currentRecibo);
            }
        }
    }
}
