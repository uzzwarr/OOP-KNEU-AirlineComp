package Lab3;

import Lab2.Crew;
import Lab2.Flight;

public class VIPFlightDecorator extends Flight {

    private Flight baseFlight;
    private String extraService;


    public VIPFlightDecorator(Flight baseFlight) {
        super(baseFlight.getFlightNumber(), baseFlight.getPassengerCount(), baseFlight.getFuelAmount(), baseFlight.getFlightCrew());
        this.baseFlight = baseFlight;

        this.extraService = "Free Wi-FI and access to the restaurant";

    }

    public Flight getBaseFlight() {
        return baseFlight;
    }

    public String getExtraService() {
        return extraService;
    }

    @Override
    public String toString() {
        return super.toString() + " VIP-Service: " + this.extraService;
    }
}
