package Lab8.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// dto для передачі рейсу через REST (без циклічних зв'язків)
public class FlightDto {

    private Long id;

    @NotBlank(message = "Номер рейсу не може бути порожнім")
    private String flightNumber;

    @NotBlank(message = "Місто відправлення обов'язкове")
    private String departureCity;

    @NotBlank(message = "Місто прибуття обов'язкове")
    private String arrivalCity;

    @NotNull(message = "Час відправлення обов'язковий")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departureTime;

    @NotNull(message = "Час прибуття обов'язковий")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrivalTime;

    // список id членів екіпажу прив'язаних до рейсу
    private List<Long> crewIds = new ArrayList<>();

    // список членів екіпажу у короткому вигляді (для відображення)
    private List<CrewMemberShortDto> crew = new ArrayList<>();

    public FlightDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getDepartureCity() { return departureCity; }
    public void setDepartureCity(String departureCity) { this.departureCity = departureCity; }

    public String getArrivalCity() { return arrivalCity; }
    public void setArrivalCity(String arrivalCity) { this.arrivalCity = arrivalCity; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public List<Long> getCrewIds() { return crewIds; }
    public void setCrewIds(List<Long> crewIds) { this.crewIds = crewIds; }

    public List<CrewMemberShortDto> getCrew() { return crew; }
    public void setCrew(List<CrewMemberShortDto> crew) { this.crew = crew; }
}
