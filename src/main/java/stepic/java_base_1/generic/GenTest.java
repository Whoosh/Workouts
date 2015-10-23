package stepic.java_base_1.generic;

import java.util.*;

/**
 * Created by DX on 19.10.2015.
 */
public class GenTest<X> {

    public static void main(String[] args) {
        Set<Integer> first = new HashSet<Integer>() {
            {
            add(1);
            add(2);
            add(3);
            }
        };
        Set<Integer> second = new HashSet<Integer>() {
            {
                add(0);
                add(1);
                add(2);
            }
        };
        System.out.println(symmetricDifference(first,second));
    }

    public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
        Set<T> result = new HashSet<>();
        for (T t : set1) if(!set2.contains(t)) result.add(t);
        for (T t : set2) if(!set1.contains(t)) result.add(t);
        return result;
    }
}
