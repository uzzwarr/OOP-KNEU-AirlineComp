package Lab4.Calculator;

public class StrategyAdd implements Strategy {

    @Override
    public double execute(double a, double b) {
        return a+b;
    }
}
