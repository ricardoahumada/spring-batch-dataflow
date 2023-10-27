package com.banana.banana_invoices.batch.processor;

import com.banana.banana_invoices.models.Recibo;
import com.banana.banana_invoices.models.ReciboInvalido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Set;

public class ReciboValidoProcessor implements ItemProcessor<Recibo, Object> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object process(Recibo recibo) {
        // TODO: HERE VALIDATE RECIBO AND RETURN RECIBO IF VALID OR RECIBO_INVALIDO WITH ERRORS OTHERWISE
        return null;
    }

}
