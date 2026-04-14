package Lab4;

public class LowCostPricingStrategy implements BaggagePricingStrategy {

    @Override
    public double calculatePrice(double weight) {
        return 5 * weight;
    }
}
