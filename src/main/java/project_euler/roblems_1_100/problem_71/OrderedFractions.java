package project_euler.roblems_1_100.problem_71;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import project_euler.sub_code.DefaultBenchmark;

import java.util.concurrent.TimeUnit;

/**
 * https://projecteuler.net/problem=71
 * https://ru.wikipedia.org/wiki/%D0%A0%D1%8F%D0%B4_%D0%A4%D0%B0%D1%80%D0%B5%D1%8F
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class OrderedFractions {

    private static final int N = 1_000;
    private static final int NUMERATOR = 3;
    private static final int DENUM = 7;

    public static void main(String[] args) {
        DefaultBenchmark.run(OrderedFractions.class);
    }

    @Benchmark
    public static void run(){
        int leftNumerator = 0, leftDenum = 1, rightNumerator = 1, rightDenum = 1, resultN = 0, resultD = 0;
        while (leftDenum + rightDenum <= N) {
            resultN = leftNumerator + rightNumerator;
            resultD = leftDenum + rightDenum;
            if (resultN * DENUM > resultD * NUMERATOR || resultN == NUMERATOR && resultD == DENUM) {
                rightNumerator = resultN;
                rightDenum = resultD;
            } else {
                leftNumerator = resultN;
                leftDenum = resultD;
            }
           System.out.println(resultN + " " + resultD);
        }
    }
}
