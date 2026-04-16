package Lab5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class FlightDao {
    private  SessionFactory sessionFactory;

    public FlightDao() {
        sessionFactory = new Configuration()
                .configure("properties.xml")
                .addAnnotatedClass(Flight.class)
                .addAnnotatedClass(Airplane.class)
                .addAnnotatedClass(Passenger.class)
                .addAnnotatedClass(Passport.class)
                .buildSessionFactory();
    }

    public void save(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Метод persist автоматично визначає тип сутності завдяки анотаціям @Entity
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Flight> getAllFlights() {
        Session session = sessionFactory.openSession();

        List<Flight> flights = session.createQuery("from Flight", Flight.class).getResultList();

        session.close();
        return flights;
    }

    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
