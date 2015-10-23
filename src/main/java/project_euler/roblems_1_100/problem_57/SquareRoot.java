package project_euler.roblems_1_100.problem_57;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.DefaultBenchmark;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.math.BigInteger.valueOf;
import static project_euler.sub_code.CustomMathFunctions.log10;

/**
 * https://projecteuler.net/problem=57
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SquareRoot {

    private final static int OBSERVER_COUNT = 9999;

    public static void main(String[] args) {
        //run();
        DefaultBenchmark.run(SquareRoot.class);
    }

    @Benchmark
    public static void run() {
        long count = getSequenceOfSqrtExpansions(OBSERVER_COUNT)
                .filter(x -> log10(x.numerator) > log10(x.denominator))
                .count();
           System.out.println(count);
    }

    private static Stream<Expansion> getSequenceOfSqrtExpansions(long count) {
        return Stream.generate(new Supplier<Expansion>() {
            private BigInteger p = valueOf(7), p2 = valueOf(5);
            private BigInteger c = valueOf(17), c2 = valueOf(12);
            private BigInteger step = valueOf(2);
            private BigInteger b;

            @Override
            public Expansion get() {
                b = c;
                c = c.multiply(step).add(p);
                p = b;
                b = c2;
                c2 = c2.multiply(step).add(p2);
                p2 = b;
                return new Expansion(c, c2);
            }
        }).limit(count);
    }

    private static class Expansion {
        BigInteger numerator;
        BigInteger denominator;

        public Expansion(BigInteger n, BigInteger d) {
            this.numerator = n;
            this.denominator = d;
        }
    }
}
