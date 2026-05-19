package Lab8.service;

import Lab8.dto.CrewMemberShortDto;
import Lab8.dto.FlightDto;
import Lab8.entity.CrewMember;
import Lab8.entity.Flight;
import Lab8.repository.CrewMemberRepository;
import Lab8.repository.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

// сервіс для роботи з рейсами
@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final CrewMemberRepository crewMemberRepository;

    public FlightService(FlightRepository flightRepository, CrewMemberRepository crewMemberRepository) {
        this.flightRepository = flightRepository;
        this.crewMemberRepository = crewMemberRepository;
    }

    // отримання всіх рейсів
    public List<FlightDto> findAll() {
        return flightRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // отримання рейсу за id
    public FlightDto findById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Рейс не знайдено: id=" + id));
        return toDto(flight);
    }

    // створення нового рейсу
    public FlightDto create(FlightDto dto) {
        if (flightRepository.existsByFlightNumber(dto.getFlightNumber())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Рейс із таким номером уже існує");
        }
        Flight flight = toEntity(dto, new Flight());
        Flight saved = flightRepository.save(flight);
        return toDto(saved);
    }

    // оновлення існуючого рейсу
    public FlightDto update(Long id, FlightDto dto) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Рейс не знайдено: id=" + id));
        toEntity(dto, flight);
        Flight saved = flightRepository.save(flight);
        return toDto(saved);
    }

    // видалення рейсу
    public void delete(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Рейс не знайдено: id=" + id);
        }
        flightRepository.deleteById(id);
    }

    // конвертація entity -> dto
    private FlightDto toDto(Flight flight) {
        FlightDto dto = new FlightDto();
        dto.setId(flight.getId());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setDepartureCity(flight.getDepartureCity());
        dto.setArrivalCity(flight.getArrivalCity());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        if (flight.getCrew() != null) {
            dto.setCrewIds(flight.getCrew().stream().map(CrewMember::getId).collect(Collectors.toList()));
            dto.setCrew(flight.getCrew().stream()
                    .map(c -> new CrewMemberShortDto(c.getId(), c.getFullName(), c.getRole()))
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    // конвертація dto -> entity (target вже може існувати, для update)
    private Flight toEntity(FlightDto dto, Flight target) {
        target.setFlightNumber(dto.getFlightNumber());
        target.setDepartureCity(dto.getDepartureCity());
        target.setArrivalCity(dto.getArrivalCity());
        target.setDepartureTime(dto.getDepartureTime());
        target.setArrivalTime(dto.getArrivalTime());

        // прив'язуємо членів екіпажу за id
        if (dto.getCrewIds() != null && !dto.getCrewIds().isEmpty()) {
            List<CrewMember> crew = crewMemberRepository.findAllById(dto.getCrewIds());
            target.setCrew(crew);
        } else {
            target.getCrew().clear();
        }
        return target;
    }
}
