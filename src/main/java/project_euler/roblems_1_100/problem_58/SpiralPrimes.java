package project_euler.roblems_1_100.problem_58;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.DefaultBenchmark;

import java.util.concurrent.TimeUnit;

/**
 * https://projecteuler.net/problem=58
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SpiralPrimes {

    public static void main(String[] args) {
        DefaultBenchmark.run(SpiralPrimes.class);
    }

    @Benchmark
    public static void run() {
        double countOnDiagonal = 1, primeCount = 0;
        for (long i = 3, squareSize = 3; i < Long.MAX_VALUE; i += 2) {
            for (long j = squareSize; j <= i * i; j += i - 1)
                if (CustomMathFunctions.isPrime(j)) primeCount++;
            squareSize = i * i;
            countOnDiagonal += 4;
            if (primeCount / countOnDiagonal < 0.10) break;
        }
    }
}
