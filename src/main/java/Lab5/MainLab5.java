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

        Flight flight1 = new Flight("PS067", "Melbourne", LocalDateTime.now(), LocalDateTime.now().plusHours(7));


        flight1.setAirplane(mriya);

        dao.save(flight1);

        List<Flight> allMyFlights = dao.getAllFlights();

        System.out.println(allMyFlights);
        System.out.println("The flight has been saved, amaze-amaze-amaze!");
    }
}
