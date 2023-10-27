package com.banana.banana_invoices.persistence;

import com.banana.banana_invoices.models.Propietario;

import java.util.List;

public interface IPropietarioRepo {
    public List<Propietario> findAll();
    public Propietario save(Propietario prop);
    Propietario findById(Long propId);
}
