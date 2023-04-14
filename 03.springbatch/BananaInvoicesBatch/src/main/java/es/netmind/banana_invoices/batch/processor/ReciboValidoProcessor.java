package es.netmind.banana_invoices.batch.processor;

import es.netmind.banana_invoices.models.Recibo;
import es.netmind.banana_invoices.models.ReciboInvalido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Set;

public class ReciboValidoProcessor implements ItemProcessor<Recibo, Object> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object process(Recibo recibo) {
        // TODO: HERE GET PAID STATUS AND PROCESS RECIBO
        Set<String> errores = recibo.esValido();

        if (errores.size() > 0) {
            recibo.setValido(false);
            return new ReciboInvalido(recibo, String.join("|", errores));

        } else {
            recibo.setValido(true);
            return recibo;
        }
    }

}
