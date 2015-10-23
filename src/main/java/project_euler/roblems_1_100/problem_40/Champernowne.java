package project_euler.roblems_1_100.problem_40;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

/**
 * https://projecteuler.net/problem=40
 */

public class Champernowne implements Starter {

    private static final long PROBLEM = 1000000L;
    private static long[] limits;

    public static void main(String[] args) {
        new Champernowne().start();
    }

    @StartWithLazzyBenchmark
    public void run(){
        prepareLimits();
        int k = 2, result = 1, number;
        for (long d = 100; k < limits.length; d *= 10, k++) {
            number = (int) ((d - limits[k - 1]) / k + ninesFor(k - 1));
            result *= CustomMathFunctions.numberToArray(number)[(int) (((d - limits[k - 1]) % k) * k) / 10];
        }
        System.out.println(result);
    }

    private static long ninesFor(long limit) {
        if (limit == 0) return 0;
        long result = 9;
        for (long i = 1; i < limit; i++)
            result = result * 10 + 9;
        return result;
    }

    private static void prepareLimits() {
        limits = new long[CustomMathFunctions.valLen(PROBLEM)];
        int k = 0;
        for (long dIndex = 10; ++k < limits.length; dIndex *= 10)
            limits[k] = dIndex * k - sumAllBefore(dIndex);
    }

    private static long sumAllBefore(long val) {
        long sum = 0;
        for (long i = val / 10; i != 0; i /= 10)
            sum += i;
        return sum;
    }
}
