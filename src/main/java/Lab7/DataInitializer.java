package Lab7;

import Lab7.AdminUser;
import Lab7.repository.AdminUserRepository;
import Lab7.services.FlightService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final FlightService flightService;

    private final AdminUserRepository adminUserRepository;

    public DataInitializer(FlightService flightService, AdminUserRepository adminUserRepository) {
        this.flightService = flightService;
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        AdminUser admin = new AdminUser("admin", "1234");
        adminUserRepository.save(admin); // пароль: 1234

        Flight flight1 = new Flight.Builder()

                .flightNumber("KNEU-FK67")
                .departureCity("New York")
                .arrivalCity("Kharkiv")
                .departureTime(LocalDateTime.now().plusHours(1))
                .arrivalTime(LocalDateTime.now().plusDays(1).plusHours(2))
                .build();

        flightService.save(flight1);

        Flight flight2 = new Flight.Builder()
                .flightNumber("KNEU-777")
                .departureCity("Toronto")
                .arrivalCity("Tokyo")
                .departureTime(LocalDateTime.now().plusHours(1))
                .arrivalTime(LocalDateTime.now().plusDays(1).plusHours(2))
                .build();

        flight2.setDepartureTime(LocalDateTime.now().plusHours(1));
        flight2.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(1));

        flightService.save(flight2);

        System.out.println("The database has been successfully populated with test flights!");
    }

}


