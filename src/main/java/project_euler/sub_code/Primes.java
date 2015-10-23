package project_euler.sub_code;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.HashSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Primes {

    public static IntStream getPrimes(int end) {
        BitSet checkedMap = new BitSet();
        checkedMap.set(2, end, true);
        long k;
        for (int i = 2; (k = i * i) < end; i++) {
            for (int j = (int) k; j < end && k < Integer.MAX_VALUE; j += i) {
                checkedMap.set(j, false);
            }
        }
        return checkedMap.stream();
    }

    public static Stream<BigInteger> bigPrimes(BigInteger start, BigInteger end) {
        final HashSet<BigInteger> badNumbers = new HashSet<>();
        for (BigInteger i = BigInteger.valueOf(2), k = i; k.compareTo(end) <= 0; k = i.multiply(i), i = i.add(BigInteger.ONE)) {
            k = i.multiply(i);
            for (BigInteger j = k; j.compareTo(end) <= 0; j = j.add(i)) {
                if (j.compareTo(start) >= 0) badNumbers.add(j);
            }
        }
        return Stream
                .iterate(start, x -> x.add(BigInteger.ONE))
                .filter(x -> x.compareTo(start) >= 0)
                .filter(x -> !badNumbers.contains(x))
                .limit(end.subtract(start).longValue() - badNumbers.size());
    }
}
