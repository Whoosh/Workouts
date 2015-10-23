package project_euler.roblems_1_100.problem_70;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.DefaultBenchmark;
import project_euler.sub_code.EulerFunctions;
import project_euler.sub_code.Primes;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static project_euler.sub_code.CustomMathFunctions.numberToArray;

/**
 * https://projecteuler.net/problem=70
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class TotientPermutation {

    private static final int N = 10_000_000;
    private static EulerFunctions eulerFunctions = new EulerFunctions(Primes.getPrimes(N)
            .boxed()
            .collect(Collectors.toCollection(TreeSet::new)));

    public static void main(String[] args) {
        DefaultBenchmark.run(TotientPermutation.class);
    }

    @Benchmark
    public static void run() {
        double result = 100, i = 0, c;
        long b;
        for (double n = N / 2; n < N; n++) {
            b = eulerFunctions.totient((int) n);
            c = n / b;
            if (isPermutation((int) n, (int)b) && result > c) {
                result = c;
                i = n;
            }
        }
        // System.out.println(result + " " + i);
    }

    private static boolean isPermutation(int a, int b) {
        final Integer[] aDigits = CustomMathFunctions.numberToArray(a);
        final Integer[] bDigits = CustomMathFunctions.numberToArray(b);
        if (aDigits.length != bDigits.length) return false;
        Arrays.sort(aDigits);
        Arrays.sort(bDigits);
        for (int i = 0; i < aDigits.length; i++) {
            if (!aDigits[i].equals(bDigits[i])) return false;
        }
        return true;
    }
}
