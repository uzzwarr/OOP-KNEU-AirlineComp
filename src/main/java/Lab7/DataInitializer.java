package Lab7;

import Lab7.Flight;
import Lab7.services.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FlightService flightService;

    public DataInitializer(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public void run(String... args) throws Exception {

        Flight flight1 = new Flight();

        flight1.setFlightNumber("KNEU-FK67");
        flight1.setDepartureCity("New York");
        flight1.setArrivalCity("Kharkiv");

        flight1.setDepartureTime(LocalDateTime.now().plusHours(1));
        flight1.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(2));

        flightService.save(flight1);

        Flight flight2 = new Flight();

        flight2.setFlightNumber("KNEU-FK96");
        flight2.setDepartureCity("Toronto");
        flight2.setArrivalCity("Tokyo");

        flight2.setDepartureTime(LocalDateTime.now().plusHours(1));
        flight2.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(1));

        flightService.save(flight2);

        System.out.println("The database has been successfully populated with test flights!");
    }
}


