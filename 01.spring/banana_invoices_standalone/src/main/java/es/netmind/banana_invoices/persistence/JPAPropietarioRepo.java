package es.netmind.banana_invoices.persistence;

import es.netmind.banana_invoices.models.Propietario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class JPAPropietarioRepo implements IPropietarioRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Propietario> findAll() {
        Query query = em.createQuery("SELECT e FROM Propietario e");
        return (List<Propietario>) query.getResultList();
    }
}
