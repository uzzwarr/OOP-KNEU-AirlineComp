public class Administrator {
    private String adminName;
    private String workShift;

    public Administrator(String adminName, String workShift) {
        this.adminName = adminName;
        this.workShift = workShift;
    }

    public void addNewFlight(Airline airline, Flight flight) {
        airline.addFlight(flight);
    }
}
