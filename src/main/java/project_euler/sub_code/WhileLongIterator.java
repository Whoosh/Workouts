package project_euler.sub_code;

import java.util.Iterator;
import java.util.PrimitiveIterator;
import java.util.function.Predicate;

public class WhileLongIterator implements PrimitiveIterator.OfLong {

    private Long value;
    private Predicate<Long> predicate;
    private Iterator<Long> iterator;

    public WhileLongIterator(Iterator<Long> iterator, Predicate<Long> predicate) {
        this.predicate = predicate;
        this.iterator = iterator;
    }

    @Override
    public long nextLong() {
        return value;
    }

    @Override
    public boolean hasNext() {
        if (iterator.hasNext()) {
            value = iterator.next();
            if (predicate.test(value)) {
                return true;
            }
        }
        return false;
    }
}
