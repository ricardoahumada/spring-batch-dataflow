package es.netmind.banana_invoices.services;

import es.netmind.banana_invoices.models.Propietario;
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
    private IReciboRepo recivosRepo;

    @Override
    public List<Propietario> findAll() {
        return propietariosRepo.findAll();
    }

    @Override
    public Propietario save(Propietario prop) {
        return propietariosRepo.save(prop);
    }
}
