package es.netmind.banana_invoices.services;

import es.netmind.banana_invoices.models.Propietario;
import es.netmind.banana_invoices.models.Recibo;

import java.util.List;

public interface IInventario {
    public List<Propietario> findAllPropietarios();

    public Propietario savePropietario(Propietario prop);

    public List<Recibo> findAllRecibos();

    public Recibo saveRecibo(Recibo rec);

    public Recibo asocia(Recibo rec, Propietario prop);

    public boolean esValidoRecibo(Long id);

    public boolean estaPagadoRecibo(Long id);

}
