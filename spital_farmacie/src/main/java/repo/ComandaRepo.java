package repo;

import domain.Comanda;
import domain.Stare;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repo.interfete.RepoComanda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComandaRepo implements RepoComanda {
    private SessionFactory sessionFactory;

    public ComandaRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Comanda get_oneCom(Long id) {
        return null;
    }

    @Override
    public Iterable<Comanda> findAll() {
        List<Comanda> lis=new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                lis =session.createQuery("FROM Comanda", Comanda.class).getResultList();


                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
            return lis;
        }
    }

    @Override
    public void add(Comanda entity) {

        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(entity);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    @Override
    public void update(Comanda entity) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Comanda com = (Comanda)session.get(Comanda.class, entity.getId());
                com.setStare( entity.getStare() );
                session.update(com);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }

    }

    @Override
    public void delete(Comanda entity) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                Comanda employee = (Comanda)session.get(Comanda.class, entity.getId());
                session.delete(employee);
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
        }
    }

}
