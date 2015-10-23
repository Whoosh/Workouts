package project_euler.roblems_1_100.problem_21;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

import java.util.stream.LongStream;

/**
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * Evaluate the sum of all the amicable numbers under 10000.
 * <p>
 * https://projecteuler.net/problem=21
 */
public class AmicableNumbers implements Starter {

    public static void main(String[] args) {
        new AmicableNumbers().start();
    }

    @StartWithLazzyBenchmark
    public void run() {
        System.out.println(LongStream.iterate(2, x -> x + 2).limit(5000).filter(this::isAmicable).sum());
    }

    private boolean isAmicable(long a) {
        final long b = CustomMathFunctions.sumDividersOf(a);
        return a == CustomMathFunctions.sumDividersOf(b) && a != b;
    }
}
