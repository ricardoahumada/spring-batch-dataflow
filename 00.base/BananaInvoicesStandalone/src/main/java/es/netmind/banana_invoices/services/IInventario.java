package es.netmind.banana_invoices.services;

import es.netmind.banana_invoices.models.Propietario;

import java.util.List;

public interface IInventario {
    public List<Propietario> findAll();

    public Propietario save(Propietario prop);
}
