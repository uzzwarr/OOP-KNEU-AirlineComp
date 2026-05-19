package Lab4;

public class BaggageCalculator {

    private BaggagePricingStrategy strategy;

    public void setStrategy(BaggagePricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double weight) {
        return this.strategy.calculatePrice(weight);
    }
}
