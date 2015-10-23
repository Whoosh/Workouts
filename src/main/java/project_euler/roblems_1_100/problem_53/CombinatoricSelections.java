package project_euler.roblems_1_100.problem_53;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.DefaultBenchmark;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * https://projecteuler.net/problem=53
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CombinatoricSelections {

    private final static int FACT_COUNT = 100;
    private final static int point = 1_000_000;
    private final static Map<Integer, BigInteger> factorials = new HashMap<>(FACT_COUNT);

    public static void main(String[] args) {
        DefaultBenchmark.run(CombinatoricSelections.class);
    }

    @Benchmark
    public static long run() {
        fillFactorials();
        long result = 0;
        for (int n = 23; n <= FACT_COUNT; n++) {
            for (int r = 4; r <= n; r++) {
                if (limitOnPoint(n, r)) {
                    result += n - r * 2 + 1;
                    break;
                }
            }
        }
        //System.out.println(result);
        return result;
    }

    private static boolean limitOnPoint(int n, int r) {
        return combFormula(n, r).compareTo(BigInteger.valueOf(point)) >= 0;
    }

    private static BigInteger combFormula(int n, int r) {
        return factorials.get(n).divide(factorials.get(r).multiply(factorials.get(n - r)));
    }

    private static void fillFactorials() {
        factorials.put(0, BigInteger.valueOf(1));
        factorials.put(1, BigInteger.valueOf(1));
        for (int i = 2; i <= FACT_COUNT; i++) {
            factorials.put(i, calcFactorial(i));
        }
    }

    private static BigInteger calcFactorial(Integer k) {
        return factorials.get(k - 1).multiply(BigInteger.valueOf(k));
    }
}
