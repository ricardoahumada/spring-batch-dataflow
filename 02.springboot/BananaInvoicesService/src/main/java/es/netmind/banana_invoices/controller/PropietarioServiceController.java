package es.netmind.banana_invoices.controller;

import es.netmind.banana_invoices.models.Propietario;
import es.netmind.banana_invoices.models.StatusMessage;
import es.netmind.banana_invoices.services.IInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propietarios")
@Validated
public class PropietarioServiceController {

    @Autowired
    IInventario inventario;

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Propietario>> getAllPropietarios() {
        List<Propietario> props = inventario.findAllPropietarios();
        return new ResponseEntity<>(props, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPropietario(@RequestBody Propietario newPropietario) {
        newPropietario.setPid(null);
        inventario.savePropietario(newPropietario);
        if (newPropietario != null && newPropietario.getPid() > 0) return new ResponseEntity(newPropietario, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "Datos incorrectos"), HttpStatus.BAD_REQUEST);
    }

}
