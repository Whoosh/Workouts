package stepic.java_base_1.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by DX on 20.10.2015.
 */
public class MinMax {

    public static void main(String[] args) {

    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        ArrayList<T> collect = stream.sorted(order).collect(Collectors.toCollection(ArrayList::new));
        if (collect.isEmpty()) minMaxConsumer.accept(null, null);
        else minMaxConsumer.accept(collect.get(0), collect.get(collect.size() - 1));
    }
}
