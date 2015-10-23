package project_euler.sub_code;

import java.util.PrimitiveIterator;
import java.util.function.Predicate;

public class WhileDoubleIterator implements PrimitiveIterator.OfDouble {

    private Double value;
    private Predicate<Double> predicate;
    private OfDouble ofDouble;

    public WhileDoubleIterator(OfDouble ofDouble, Predicate<Double> predicate) {
        this.predicate = predicate;
        this.ofDouble = ofDouble;
    }

    @Override
    public boolean hasNext() {
        if (ofDouble.hasNext()) {
            value = ofDouble.next();
            if (predicate.test(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double nextDouble() {
        return value;
    }
}
