package es.netmind.banana_invoices.services;

import es.netmind.banana_invoices.models.Propietario;
import es.netmind.banana_invoices.models.Recibo;
import es.netmind.banana_invoices.persistence.IPropietarioRepo;
import es.netmind.banana_invoices.persistence.IReciboRepo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class InventarioImpl implements IInventario {
    @Getter
    @Setter
    private IPropietarioRepo propietariosRepo;

    @Getter
    @Setter
    private IReciboRepo recibosRepo;

    @Override
    public List<Propietario> findAllPropietarios() {
        return propietariosRepo.findAll();
    }

    @Override
    public Propietario savePropietario(Propietario prop) {
        return propietariosRepo.save(prop);
    }

    @Override
    public List<Recibo> findAllRecibos() {
        return recibosRepo.findAll();
    }

    @Override
    public Recibo saveRecibo(Recibo rec) {
        return recibosRepo.save(rec);
    }

    @Override
    public Recibo asocia(Recibo rec, Propietario prop) {
        rec.setPropietario(prop);
        recibosRepo.save(rec);
        return rec;
    }

    @Override
    public boolean esValidoRecibo(Long id) {
        Recibo rec = recibosRepo.findById(id);
        if (rec != null) return rec.esValido();
        else return false;
    }

    @Override
    public boolean estaPagadoRecibo(Long id) {
        Recibo rec = recibosRepo.findById(id);
        if (rec != null) return rec.isEstado();
        else return false;
    }
}
