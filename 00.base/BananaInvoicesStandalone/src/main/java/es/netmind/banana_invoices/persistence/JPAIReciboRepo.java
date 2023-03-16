package es.netmind.banana_invoices.persistence;

import es.netmind.banana_invoices.models.Recibo;

import javax.persistence.EntityManager;
import java.util.List;

public class JPAIReciboRepo implements IReciboRepo{

    private EntityManager em;

    @Override
    public List<Recibo> findAll() {
        return null;
    }
}
