package Lab5;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    private  String passportNumb;

    @ManyToMany(mappedBy = "passengers")
    private List<Flight> flights = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "passport_id")
    private Passport passport;

    public Passenger() {}

    public Passenger(String fullName, String passportNumb) {
        this.fullName = fullName;
        this.passportNumb = passportNumb;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassportNumb() {
        return passportNumb;
    }

    public void setPassportNumb(String passportNumb) {
        this.passportNumb = passportNumb;
    }
}
