package Lab3;

import Lab2.*;

public class AirlineFacade {
    private Airline airline;
    private  Dispatcher dispatcher;
    private  Administrator administrator;

    public AirlineFacade(Airline airline, Dispatcher dispatcher, Administrator administrator) {
        this.airline = airline;
        this.dispatcher = dispatcher;
        this.administrator = administrator;
    }

    public void organizeFlight(String chiefPilot, String coPilot, String radioOperator,String flightNumber, int passengerCount, double fuelAmount) {
        Crew newCrew = new Crew(chiefPilot, coPilot, radioOperator);
        Flight newFlight = new Flight(flightNumber, passengerCount, fuelAmount, null);

        this.dispatcher.assignCrew(newFlight, newCrew);
        this.administrator.addNewFlight(this.airline, newFlight);
    }


}
