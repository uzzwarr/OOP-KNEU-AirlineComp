package Lab6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;


public class TicketDao implements GenericDao<Ticket> {

    private SessionFactory sessionFactory;

    public TicketDao() {
        sessionFactory = new Configuration()
                .configure("properties.xml")
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    @Override
    public void save(Ticket entity) {
        if (entity.getPrice() < 0) {
            throw new IllegalArgumentException("Ціна квитка не може бути від'ємною!");
        }

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Ticket findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    @Override
    public List<Ticket> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Ticket", Ticket.class).getResultList();
        }
    }

    @Override
    public void update(Ticket entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Ticket entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public List<Ticket> findByNativeQuery(String sql) {
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery(sql, Ticket.class).getResultList();
        }
    }

    @Override
    public List<Ticket> findByHqlQuery(String hql) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(hql, Ticket.class).getResultList();
        }
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}