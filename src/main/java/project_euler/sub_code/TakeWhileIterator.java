package project_euler.sub_code;

import java.util.Iterator;
import java.util.function.Predicate;

public class TakeWhileIterator<T> implements Iterator<T> {

    private final Predicate<T> stopPredicate;
    private final Iterator<T> iterator;

    private T element;

    public TakeWhileIterator(Iterator<T> iterator, Predicate<T> stopPredicate) {
        this.stopPredicate = stopPredicate;
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        if (iterator.hasNext()) {
            element = iterator.next();
            if (stopPredicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T next() {
        return element;
    }
}