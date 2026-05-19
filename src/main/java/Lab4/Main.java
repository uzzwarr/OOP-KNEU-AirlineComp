package Lab4;

import Lab2.Administrator;
import Lab2.Dispatcher;

public class Main {
    public static void main(String[] args) {

        ObservableFlight myFlight = new ObservableFlight("OBS067", 167, 6700.1, null);

        AdminSubscriber myAdminSubscriber = new AdminSubscriber();
        DispatcherSubscriber myDispatcherSubscriber = new DispatcherSubscriber();

        myFlight.addObserver(myAdminSubscriber);
        myFlight.addObserver(myDispatcherSubscriber);

        myFlight.notifyObservers("The flight is delayed due to meatball-shaped precipitation");

        BaggageCalculator myBaggageCalculator = new BaggageCalculator();
        StandardPricingStrategy myStandardPricingStrategy = new StandardPricingStrategy();

        myBaggageCalculator.setStrategy(myStandardPricingStrategy);

        System.out.println(myBaggageCalculator.calculate(20));

    }


}
