package Lab8.dto;

// скорочене представлення члена екіпажу для вкладеного відображення у рейсі
public class CrewMemberShortDto {

    private Long id;
    private String fullName;
    private String role;

    public CrewMemberShortDto() {}

    public CrewMemberShortDto(Long id, String fullName, String role) {
        this.id = id;
        this.fullName = fullName;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
