package Lab3;

import Lab2.Airline;
import Lab2.Administrator;
import Lab2.Dispatcher;
import Lab2.Flight;

public class Main {
    public static void main(String[] args) {
        Dispatcher myDispatcher = new Dispatcher("007", "Rudolf West");
        Airline myAirline = new Airline("Potuzhno Airlines");
        Administrator myAdmin = new Administrator("Ryan Gosling", "Day-Shift");

        AirlineFacade facade = new AirlineFacade(myAirline, myDispatcher, myAdmin);

        facade.organizeFlight("Linus Torvalds", "Ryan Gosling", "Robert Oppenheimer", "A6767",167, 567.12);

        Flight standartFlight = new Flight("VIP007", 69, 1488.68, null);
        VIPFlightDecorator vipFlight = new VIPFlightDecorator(standartFlight);
        myAdmin.addNewFlight(myAirline, vipFlight);


        System.out.println(myAirline);
    }
}
