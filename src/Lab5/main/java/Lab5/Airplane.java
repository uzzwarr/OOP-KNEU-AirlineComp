package Lab5;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    private int capacity;


    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL)
    private List<Flight> flights = new ArrayList<>();

    public Airplane() {

    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
