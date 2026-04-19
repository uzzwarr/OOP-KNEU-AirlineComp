package Lab5;

import jakarta.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumb;

    public Passport(){}


    public Passport(String serialNumb) {
        this.serialNumb = serialNumb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumb() {
        return serialNumb;
    }

    public void setSerialNumb(String serialNumb) {
        this.serialNumb = serialNumb;
    }
}
