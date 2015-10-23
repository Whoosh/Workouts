package project_euler.roblems_1_100.problem_72;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.EulerFunctions;
import project_euler.sub_code.Primes;
import project_euler.sub_code.DefaultBenchmark;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=72
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class CountingFraction {

    private static final int N = 1_000_000;
    private static EulerFunctions eulerFunctions = new EulerFunctions(Primes
            .getPrimes(N).boxed().collect(Collectors.toCollection(TreeSet::new)));

    public static void main(String[] args) {
        DefaultBenchmark.run(CountingFraction.class);
    }

    @Benchmark
    public static void run() {
        LongStream.rangeClosed(1, N).map(x -> eulerFunctions.totient((int) x)).sum();
    }
}
