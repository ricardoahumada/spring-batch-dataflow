package com.banana.veriapi.controller;

import com.banana.veriapi.model.Status;
import com.banana.veriapi.utils.RandomGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/invoices")
@Validated
public class InvoicesVerifierServiceController {
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getProduct(@PathVariable @Min(1) Long id) {
        Status status = new Status(id, RandomGenerator.randomBoolean());
        return new ResponseEntity<>(status, HttpStatus.OK);

    }
}
