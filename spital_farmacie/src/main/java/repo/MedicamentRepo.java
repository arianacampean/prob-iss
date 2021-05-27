package repo;

import domain.Comanda;
import domain.Medicament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repo.interfete.RepoMedicament;

import java.util.ArrayList;
import java.util.List;

public class MedicamentRepo implements RepoMedicament {
    private SessionFactory sessionFactory;

    public MedicamentRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Medicament get_oneMed(long id) {
        return null;
    }

    @Override
    public Iterable<Medicament> findAll() {
        List<Medicament> lis=new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                lis =session.createQuery("FROM Medicament", Medicament.class).getResultList();


                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
            return lis;
        }
    }

    @Override
    public void add(Medicament entity) {

    }

    @Override
    public void update(Medicament entity) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Medicament com = (Medicament) session.get(Medicament.class, entity.getId());
                com.setCantitate_pe_stoc( entity.getCantitate_pe_stoc() );
                session.update(com);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void delete(Medicament entity) {

    }
}
