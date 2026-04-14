package Lab5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FlightDao {
    private  SessionFactory sessionFactory;

    public FlightDao() {
        sessionFactory = new Configuration()
                .configure("properties.xml")
                .addAnnotatedClass(Flight.class)
                .buildSessionFactory();
    }

    public void save(Flight flight) {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        try {
            session.persist(flight);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
