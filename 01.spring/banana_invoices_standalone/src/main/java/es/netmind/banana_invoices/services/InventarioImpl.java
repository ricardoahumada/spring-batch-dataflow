package es.netmind.banana_invoices.services;

import es.netmind.banana_invoices.models.Propietario;
import es.netmind.banana_invoices.persistence.IPropietarioRepo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class InventarioImpl implements IInventario{
    @Getter @Setter
    private IPropietarioRepo propietariosRepo;

    @Override
    public List<Propietario> findAll() {
        return propietariosRepo.findAll();
    }
}
