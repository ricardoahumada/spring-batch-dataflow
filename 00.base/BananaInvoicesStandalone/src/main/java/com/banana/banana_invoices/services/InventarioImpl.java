package com.banana.banana_invoices.services;

import com.banana.banana_invoices.models.Propietario;
import com.banana.banana_invoices.persistence.IPropietarioRepo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class InventarioImpl implements IInventario {
    @Getter
    @Setter
    private IPropietarioRepo propietariosRepo;

    @Override
    public List<Propietario> findAll() {
        return propietariosRepo.findAll();
    }

    @Override
    public Propietario save(Propietario prop) {
        return propietariosRepo.save(prop);
    }
}
