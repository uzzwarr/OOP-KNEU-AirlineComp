package Lab2;

public class Flight {
    private String flightNumber;
    private int passengerCount;
    private double fuelAmount;
    private Crew flightCrew;

    public Flight(String flightNumber, int passengerCount, double fuelAmount, Crew flightCrew) {
        this.flightNumber = flightNumber;
        this.passengerCount = passengerCount;
        this.fuelAmount = fuelAmount;
        this.flightCrew = flightCrew;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public Crew getFlightCrew() {
        return flightCrew;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void setFlightCrew(Crew flightCrew) {
        this.flightCrew = flightCrew;
    }

    @Override
    public String toString() {
        return "Lab2.Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", passengerCount=" + passengerCount +
                ", fuelAmount=" + fuelAmount +
                ", flightCrew=" + flightCrew +
                '}';
    }
}
