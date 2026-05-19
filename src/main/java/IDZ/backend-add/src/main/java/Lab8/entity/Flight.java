package Lab8.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// сутність рейсу авіакомпанії
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Номер рейсу не може бути порожнім")
    @Column(name = "flight_number", nullable = false, unique = true)
    private String flightNumber;

    @NotBlank(message = "Місто відправлення обов'язкове")
    @Column(name = "departure_city", nullable = false)
    private String departureCity;

    @NotBlank(message = "Місто прибуття обов'язкове")
    @Column(name = "arrival_city", nullable = false)
    private String arrivalCity;

    @NotNull(message = "Час відправлення обов'язковий")
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @NotNull(message = "Час прибуття обов'язковий")
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    // зв'язок many-to-many з членами екіпажу
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "flight_crew",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "crew_id")
    )
    @JsonIgnore
    private List<CrewMember> crew = new ArrayList<>();

    public Flight() {}

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

    public List<CrewMember> getCrew() { return crew; }
    public void setCrew(List<CrewMember> crew) { this.crew = crew; }
}
