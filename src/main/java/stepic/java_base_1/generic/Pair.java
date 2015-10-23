package stepic.java_base_1.generic;

/**
 * Created by DX on 19.10.2015.
 */
public class Pair<X, Y> {

    private X first;
    private Y second;

    private Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    public X getFirst() {
        return first;
    }

    public Y getSecond() {
        return second;
    }

    public static <X, Y> Pair<X, Y> of(X first, Y second) {
        return new Pair<>(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair<?, ?> pair = (Pair<?, ?>) o;

        return !(first != null ? !first.equals(pair.first)
                : pair.first != null) && !(second != null ?
                !second.equals(pair.second) : pair.second != null);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
