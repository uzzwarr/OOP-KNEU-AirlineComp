package Lab8;

import Lab8.entity.AdminUser;
import Lab8.entity.CrewMember;
import Lab8.entity.Flight;
import Lab8.repository.AdminUserRepository;
import Lab8.repository.CrewMemberRepository;
import Lab8.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

// заповнюємо БД початковими даними для Lab8 при першому запуску
@Component
@Order(2)
public class Lab8DataInitializer implements CommandLineRunner {

    private final FlightRepository flightRepository;
    private final CrewMemberRepository crewMemberRepository;
    private final AdminUserRepository adminUserRepository;

    public Lab8DataInitializer(FlightRepository flightRepository,
                               CrewMemberRepository crewMemberRepository,
                               AdminUserRepository adminUserRepository) {
        this.flightRepository = flightRepository;
        this.crewMemberRepository = crewMemberRepository;
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // адмін для логіну (admin / 1234)
        if (adminUserRepository.findByUsername("admin").isEmpty()) {
            adminUserRepository.save(new AdminUser("admin", "1234"));
            System.out.println("[Lab8] Створено адміна: admin / 1234");
        }

        // якщо БД порожня - додаємо тестові дані
        if (crewMemberRepository.count() == 0) {
            CrewMember c1 = crewMemberRepository.save(new CrewMember("Іваненко Іван Петрович", "Pilot", 12, "UA-001-PL"));
            CrewMember c2 = crewMemberRepository.save(new CrewMember("Петренко Олег Сергійович", "CoPilot", 7, "UA-002-CP"));
            CrewMember c3 = crewMemberRepository.save(new CrewMember("Сидоренко Марія Олексіївна", "Steward", 5, "UA-003-ST"));
            CrewMember c4 = crewMemberRepository.save(new CrewMember("Коваль Анна Володимирівна", "FlightAttendant", 3, "UA-004-FA"));
            System.out.println("[Lab8] Створено членів екіпажу: 4");

            if (flightRepository.count() == 0) {
                Flight f1 = new Flight();
                f1.setFlightNumber("KNEU-FK67");
                f1.setDepartureCity("Київ");
                f1.setArrivalCity("Львів");
                f1.setDepartureTime(LocalDateTime.now().plusDays(1).withSecond(0).withNano(0));
                f1.setArrivalTime(LocalDateTime.now().plusDays(1).plusHours(2).withSecond(0).withNano(0));
                f1.setCrew(List.of(c1, c2, c3));
                flightRepository.save(f1);

                Flight f2 = new Flight();
                f2.setFlightNumber("KNEU-777");
                f2.setDepartureCity("Одеса");
                f2.setArrivalCity("Варшава");
                f2.setDepartureTime(LocalDateTime.now().plusDays(2).withSecond(0).withNano(0));
                f2.setArrivalTime(LocalDateTime.now().plusDays(2).plusHours(3).withSecond(0).withNano(0));
                f2.setCrew(List.of(c1, c4));
                flightRepository.save(f2);

                Flight f3 = new Flight();
                f3.setFlightNumber("KNEU-204");
                f3.setDepartureCity("Харків");
                f3.setArrivalCity("Берлін");
                f3.setDepartureTime(LocalDateTime.now().plusDays(3).withSecond(0).withNano(0));
                f3.setArrivalTime(LocalDateTime.now().plusDays(3).plusHours(4).withSecond(0).withNano(0));
                f3.setCrew(List.of(c2, c3, c4));
                flightRepository.save(f3);

                System.out.println("[Lab8] Створено тестових рейсів: 3");
            }
        }
    }
}
