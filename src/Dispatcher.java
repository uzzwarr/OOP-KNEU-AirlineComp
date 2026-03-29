public class Dispatcher {
    private String dispName;
    private String dispID;

    public Dispatcher(String dispID, String dispName) {
        this.dispID = dispID;
        this.dispName = dispName;
    }

    public void assignCrew(Flight flight, Crew crew) {
        flight.setFlightCrew(crew);
    }
}
