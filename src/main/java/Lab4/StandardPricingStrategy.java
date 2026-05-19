package Lab4;

public class StandardPricingStrategy implements BaggagePricingStrategy {

    @Override
    public double calculatePrice(double weight) {
        return 10 + (2 * weight);
    }
}
