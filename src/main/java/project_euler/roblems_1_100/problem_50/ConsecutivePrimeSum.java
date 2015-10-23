package project_euler.roblems_1_100.problem_50;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.DefaultBenchmark;
import project_euler.sub_code.Primes;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * https://projecteuler.net/problem=50
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ConsecutivePrimeSum {

    private static final int PROBLEM = 1000000;

    public static void main(String[] args) {
        DefaultBenchmark.run(ConsecutivePrimeSum.class);
    }

    @Benchmark
    public static void run() {
        List<Integer> primes = Primes.getPrimes(PROBLEM).boxed().collect(Collectors.toList());
        int sum = 0;
        for (Integer prime : primes) {
            sum += prime;
            if (sum + prime > PROBLEM) {
                for (int j = 0; !CustomMathFunctions.isPrime(sum); j++) sum -= primes.get(j);
                //System.out.println(sum);
                break;
            }
        }
    }
}
