package com.banana.banana_invoices.controller;

import com.banana.banana_invoices.models.Recibo;
import com.banana.banana_invoices.models.ReciboPropietario;
import com.banana.banana_invoices.models.StatusMessage;
import com.banana.banana_invoices.services.IInventario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/recibos")
@Validated
public class ReciboServiceController {
    private static Logger logger = LoggerFactory.getLogger(ReciboServiceController.class);

    @Autowired
    IInventario inventario;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Recibo>> getAllRecibos() {
        List<Recibo> recs = inventario.findAllRecibos();
        return new ResponseEntity<>(recs, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRecibo(@RequestBody Recibo newRecibo) {
        newRecibo.setId(null);
        inventario.saveRecibo(newRecibo);
        if (newRecibo != null && newRecibo.getId() > 0) return new ResponseEntity(newRecibo, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "Datos incorrectos"), HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity asociateReciboPropietario(@PathVariable  @Min(1) Long id, @RequestBody ReciboPropietario recProp) {
        if (!id.equals(recProp.getRecibo()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No coincide id recibo con datos");
        Recibo rec = inventario.asocia(recProp.getRecibo(), recProp.getPropietario());
        if (rec != null && rec.getPropietario() != null) return new ResponseEntity(rec, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "Datos incorrectos"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}/isvalid")
    public ResponseEntity esValido(@PathVariable @Min(1) Long id) {
        Set<String> invalidItems = inventario.esValidoRecibo(id);
        logger.info("invalidItems:"+invalidItems);
        if (invalidItems.size() == 0) return new ResponseEntity(invalidItems, HttpStatus.OK);
        else return new ResponseEntity(invalidItems, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value = "/{id}/ispaid")
    public ResponseEntity estaValidoPagado(@PathVariable  @Min(1) Long id) {
        boolean pagado = inventario.estaPagadoRecibo(id);
        if (pagado) return new ResponseEntity(pagado, HttpStatus.OK);
        else return new ResponseEntity(pagado, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
