package com.banana.banana_invoices.services;

import com.banana.banana_invoices.exceptions.NullElementException;
import com.banana.banana_invoices.models.Propietario;
import com.banana.banana_invoices.models.Recibo;

import java.util.List;
import java.util.Set;

public interface IInventario {
    public List<Propietario> findAllPropietarios();

    public Propietario savePropietario(Propietario prop)throws NullElementException;

    public List<Recibo> findAllRecibos();

    public Recibo saveRecibo(Recibo rec)throws NullElementException;

    public Recibo asocia(Long recId, Long propId)throws NullElementException;

    public Set<String> esValidoRecibo(Long id);

    public boolean estaPagadoRecibo(Long id);

}
