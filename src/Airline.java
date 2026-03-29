import java.util.ArrayList;

public class Airline {
    private String name;
    private ArrayList<Flight> flights;

    public Airline(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public String toString() {
        return "Airline{" +
                "name='" + name + '\'' +
                ", flights=" + flights +
                '}';
    }
}
