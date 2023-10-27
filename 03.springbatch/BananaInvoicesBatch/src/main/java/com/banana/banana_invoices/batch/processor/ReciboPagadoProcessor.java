package com.banana.banana_invoices.batch.processor;

import com.banana.banana_invoices.models.PaidStatus;
import com.banana.banana_invoices.models.Recibo;
import com.banana.banana_invoices.models.ReciboInvalido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ReciboPagadoProcessor implements ItemProcessor<Recibo, Object> {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //TODO: HERE INJECT PROPERTY api.verification.url
    @Value("${api.verification.url}")
    private String apiUrl;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Recibo process(Recibo recibo) throws Exception {
        // TODO: HERE GET PAID STATUS AND PROCESS RECIBO
        PaidStatus paid_status = fetchVerificationDataFromAPI(recibo.getId());
        recibo.setEstado(paid_status.getPaid());

        return recibo;
    }

    private PaidStatus fetchVerificationDataFromAPI(Long id) throws NoSuchFieldException {
        if (apiUrl != null) {
            String endpoint = apiUrl + id;
            ResponseEntity<PaidStatus> response = restTemplate.getForEntity(endpoint, PaidStatus.class);
            PaidStatus veriData = response.getBody();
            logger.info("veriData:" + veriData);
            return veriData;
        } else throw new NoSuchFieldException("apiUrl not defined");
    }
}
