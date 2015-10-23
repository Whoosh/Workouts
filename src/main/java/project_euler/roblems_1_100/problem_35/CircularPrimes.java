package project_euler.roblems_1_100.problem_35;

import project_euler.sub_code.CustomMathFunctions;

/**
 * https://projecteuler.net/problem=35
 */

public class CircularPrimes {
    private static final int BELOW_HUNDRED = 13;
    private static final int PROBLEM = 1000000;

    public static void main(String[] args) {
        int result = BELOW_HUNDRED;
        for (int i = 100; i < PROBLEM; i++) {
            if (!CustomMathFunctions.isPrime(i)) continue;
            if (isCircularPrime(i)) result++;
        }
        System.out.println(result);
    }

    private static boolean isCircularPrime(int val) {
        Integer[] num = CustomMathFunctions.numberToArray(val);
        StringBuilder buffer = new StringBuilder(num.length);
        for (int i = 0, k = 0; i < num.length * num.length + k; i++) {
            if (i % num.length == 0 && i != 0) {
                k++;
                if (!CustomMathFunctions.isPrime(Integer.parseInt(buffer.toString()))) return false;
                buffer = new StringBuilder(num.length);
            }
            buffer.append(num[(i + k) % num.length]);
        }
        return true;
    }
}
