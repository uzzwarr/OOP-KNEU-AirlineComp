package Lab7;

import Lab7.services.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Test
    void testSaveAndFindFlight() {
        Flight testFlight = new Flight.Builder()
                .flightNumber("TEST-123")
                .departureCity("Kyiv")
                .arrivalCity("San-Diego")
                .build();

        flightService.save(testFlight);

        Flight foundFlight = flightService.getFlightById(testFlight.getId());

        assertNotNull(foundFlight, "Рейс не повинен бути null!");


        assertEquals("TEST-123", foundFlight.getFlightNumber(), "Номери рейсів мають збігатися!");

        assertEquals("Kyiv", foundFlight.getDepartureCity());
        }
    }
