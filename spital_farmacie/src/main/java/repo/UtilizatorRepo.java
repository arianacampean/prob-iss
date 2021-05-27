package repo;

import domain.Comanda;
import domain.Utilizator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repo.interfete.RepoUtilizator;

import java.util.ArrayList;

public class UtilizatorRepo implements RepoUtilizator {
    private SessionFactory sessionFactory;

    public UtilizatorRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Utilizator get_oneUtil(String username,String parola) {
        Utilizator arb=null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                arb =session.createQuery("FROM Utilizator E WHERE E.username= :username and E.parola=:parola", Utilizator.class)
                        .setParameter("username",username).setParameter("parola",parola).getSingleResult();

                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
            return arb;
        }
    }

    @Override
    public Iterable<Utilizator> findAll() {
        Iterable<Utilizator> lis=new ArrayList<>();
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                lis =session.createQuery("FROM Utilizator", Utilizator.class).getResultList();


                tx.commit();
            } catch (RuntimeException ex) {
                if (tx != null)
                    tx.rollback();
            }
            return lis;
        }
    }

    @Override
    public void add(Utilizator entity) {

    }

    @Override
    public void update(Utilizator entity) {

    }

    @Override
    public void delete(Utilizator entity) {

    }
}
