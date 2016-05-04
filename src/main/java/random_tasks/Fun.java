package random_tasks;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by whoosh on 2/14/16.
 */
public class Fun {
    private static SortedSet<Integer> s = new TreeSet<>();

    public static void main(String[] args) {
        LinkedHashMap<Integer,Integer> e = new LinkedHashMap<>(1,1f,true);
        SortedSet<Integer> hah = new TreeSet<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            s.add(random.nextInt(100));
        }
        System.out.println(s);

    }

    public static int pow(int val, int ex) {
        if (ex == 1) return val;
        return pow(val, ex - 1) * val;
    }

    public static boolean isPrime(int val) {
        if (val > 2 && (val & 1) == 0) return false;
        return !IntStream.range(2, (int) Math.sqrt(val)).filter(x -> val % x == 0).findAny().isPresent();
    }

    public static Collection<Integer> getPrimes(int to) {
        BitSet map = new BitSet();
        map.set(2, to, true);
        int k;
        for (int i = 2; (k = i * i) <= to; i++) {
            for (int j = k; j <= to; j += i) {
                map.set(j, false);
            }
        }
        return map.stream().boxed().collect(Collectors.toCollection(ArrayList::new));
    }

}
