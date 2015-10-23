package project_euler.roblems_1_100.problem_55;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.DefaultBenchmark;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

/**
 * https://projecteuler.net/problem=55
 * http://en.wikipedia.org/wiki/Lychrel_number
 * нет алгоритма кроме лома.
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LychrelNumbers {

    private static final int ITERATION_LIMIT = 50;
    private static final int CANDIDATE_COUNT = 10_000;

    public static void main(String[] args) {
        run();
        DefaultBenchmark.run(LychrelNumbers.class);
    }

    @Benchmark
    public static void run() {
        int result = 0;
        for (int i = 0; i < CANDIDATE_COUNT; i++) {
            if (checkValue(BigInteger.valueOf(i))) result++;
        }
        // System.out.println(CANDIDATE_COUNT - result);
    }

    private static boolean checkValue(BigInteger val) {
        for (int i = 0; i <= ITERATION_LIMIT; i++) {
            val = val.add(reverse(val));
            if (isPalindrome(val)) return true;
        }
        return false;
    }

    private static boolean isPalindrome(BigInteger val) {
        final String sVal = val.toString();
        final int len = sVal.length();
        for (int i = 1; i < len / 2 + 1; i++)
            if (sVal.charAt(i - 1) != sVal.charAt(len - i)) return false;
        return true;
    }

    private static BigInteger reverse(BigInteger val) {
        StringBuilder builder = new StringBuilder();
        final String rev = val.toString();
        for (int i = rev.length() - 1; i >= 0; i--) builder.append(rev.charAt(i));
        return new BigInteger(builder.toString());
    }
}
