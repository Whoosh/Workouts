package project_euler.roblems_1_100.problem_33;

import java.math.BigInteger;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=33
 */

public class DigitCancelling {

    public static void main(String[] args) {
        int d = 1, n = 1;
        for (int i = 1; i < 10; i++) {
            for (int denominator = 1; denominator < i; denominator++) {
                final int fDenominator = denominator;
                n *= getV(getND(denominator, i).reduce((x, y) -> x * x));
                d *= getV(getND(denominator, i).map(x -> fDenominator).reduce((x, y) -> x * x));
            }
        }
        System.out.println(d / BigInteger.valueOf(n).gcd(BigInteger.valueOf(d)).intValue());
    }

    private static int getV(OptionalInt reduce) {
        return reduce.isPresent() ? reduce.getAsInt() : 1;
    }

    public static IntStream getND(int d, int i) {
        return IntStream.range(1, d).filter(n -> (n * 10 + i) * d == n * (i * 10 + d));
    }
}
