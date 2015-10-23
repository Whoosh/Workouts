package project_euler.roblems_1_100.problem_56;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.DefaultBenchmark;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static project_euler.sub_code.CustomMathFunctions.sumOfDigits;

/**
 * https://projecteuler.net/problem=56
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class PowerfulDigit {

    public static final int LIMIT = 99;

    public static void main(String[] args) {
        DefaultBenchmark.run(PowerfulDigit.class);
    }

    @Benchmark
    public static void run() {
        long res = 0, b;
        for (int i = LIMIT; i > LIMIT - Math.log(LIMIT); i--) {
            b = sumOfDigits(BigInteger.valueOf(LIMIT).pow(i));
            if (res < b) res = b;
        }
        //System.out.println(res);
    }
}
