package project_euler.sub_code;

import java.util.PrimitiveIterator;

public class OfIntFromTo implements PrimitiveIterator.OfInt {

    private int limit;
    private int start;

    public OfIntFromTo(int start, int limit) {
        this.limit = limit;
        this.start = start;
    }

    @Override
    public int nextInt() {
        return start++;
    }

    @Override
    public boolean hasNext() {
        return start < limit;
    }
}
