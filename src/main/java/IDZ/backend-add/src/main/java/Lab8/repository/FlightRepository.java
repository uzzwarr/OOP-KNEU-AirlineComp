package Lab8.repository;

import Lab8.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    boolean existsByFlightNumber(String flightNumber);
}
