package Lab5;

import java.util.List;
import java.time.LocalDateTime;

public class MainLab5 {
    public static void main(String[] args) {
        FlightDao dao = new FlightDao();

        Airplane mriya = new Airplane();
        mriya.setModel("AN-225 Mriya");
        mriya.setCapacity(0);
        dao.save(mriya);

        Passenger p1 = new Passenger("Tanjiro Komado", "AA126752");
        Passenger p2 = new Passenger("Denji", "AA676996");
        dao.save(p1);
        dao.save(p2);

        Flight flight1 = new Flight("PS067", "Melbourne", LocalDateTime.now(), LocalDateTime.now().plusHours(7));

        flight1.setAirplane(mriya);
        flight1.getPassengers().add(p1);
        flight1.getPassengers().add(p2);

        dao.save(flight1);

        List<Flight> allMyFlights = dao.getAllFlights();

        System.out.println(allMyFlights);
        System.out.println("The flight has been saved, amaze-amaze-amaze!");

    }
}
