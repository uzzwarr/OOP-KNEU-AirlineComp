package Lab5;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FlightDaoTest {

    @Test
    public void testSaveAirplane(){
        FlightDao dao = new FlightDao();
        Airplane testPlane = new Airplane();
        testPlane.setModel("Mriya");
        testPlane.setCapacity(0);

        dao.save(testPlane);

        assertNotNull(testPlane.getId(), "The aircraft ID must not be null after saving!");
    }

    @Test
    public void testSaveFlightWithPassenger() {
        FlightDao dao = new FlightDao();

        Passport doc = new Passport("AB123456");
        Passenger person = new Passenger("David Laid", "AB7677271");
        person.setPassport(doc);

        Flight flight = new Flight("PS777", "Toronto", LocalDateTime.now(), LocalDateTime.now().plusHours(12));
        flight.getPassengers().add(person); // Зв'язок ManyToMany

        dao.save(doc);
        dao.save(person);
        dao.save(flight);


        assertNotNull(flight.getId(), "The flight must go on!");
    }
}
