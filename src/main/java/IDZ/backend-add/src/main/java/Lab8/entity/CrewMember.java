package Lab8.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

// сутність члена екіпажу
@Entity
@Table(name = "crew_members")
public class CrewMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ПІБ обов'язкове")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = "Роль обов'язкова")
    @Column(name = "role", nullable = false)
    private String role; // наприклад: Pilot, CoPilot, Steward, FlightAttendant

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "license_number")
    private String licenseNumber;

    // зворотний зв'язок many-to-many
    @ManyToMany(mappedBy = "crew", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Flight> flights = new ArrayList<>();

    public CrewMember() {}

    public CrewMember(String fullName, String role, Integer experienceYears, String licenseNumber) {
        this.fullName = fullName;
        this.role = role;
        this.experienceYears = experienceYears;
        this.licenseNumber = licenseNumber;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }

    public List<Flight> getFlights() { return flights; }
    public void setFlights(List<Flight> flights) { this.flights = flights; }
}
