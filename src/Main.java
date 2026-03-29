public class Main {
    public static void main(String[] args) {
        Crew myCrew = new Crew("Stepan Pantera", "Leonid Montana", "Vasyl Oliinyk");
        Flight myFlight = new Flight("A670", 165, 7567.4, null);
        Dispatcher myDispatcher = new Dispatcher("007", "Rudolf West");
        myDispatcher.assignCrew(myFlight, myCrew);

        Airline myAirline = new Airline("Potuzhno Airlines");
        Administrator myAdmin = new Administrator("Ryan Gosling", "Day-Shift");

        myAdmin.addNewFlight(myAirline, myFlight);

        System.out.println(myAirline);
    }
}
