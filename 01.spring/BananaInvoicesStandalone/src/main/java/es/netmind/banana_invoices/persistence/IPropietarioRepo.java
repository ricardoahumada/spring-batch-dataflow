package es.netmind.banana_invoices.persistence;

import es.netmind.banana_invoices.models.Propietario;

import java.util.List;

public interface IPropietarioRepo {
    public List<Propietario> findAll();
}
