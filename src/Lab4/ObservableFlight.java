package Lab4;

import Lab2.*;
import java.util.ArrayList;

public class ObservableFlight extends Lab2.Flight implements Subject {

    private ArrayList<Observer> observers;

    public ObservableFlight(String flightNumber, int passengerCount, double fuelAmount, Crew flightCrew) {
        super(flightNumber, passengerCount, fuelAmount, flightCrew);
        this.observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer obs: this.observers){
            obs.update(message);
        }
    }
}
