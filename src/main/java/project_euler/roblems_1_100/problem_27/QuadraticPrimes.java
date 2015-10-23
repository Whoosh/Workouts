package project_euler.roblems_1_100.problem_27;


import project_euler.sub_code.CustomMathFunctions;

/**
 * https://projecteuler.net/problem=27
 */

public class QuadraticPrimes {
    private static final int PROBLEM = 1000;

    public static void main(String[] args) {
        long k, r = 0;

        for (int a = -PROBLEM; a < PROBLEM; a++) {
            for (int b = -PROBLEM; b < PROBLEM; b++) {
                if (Math.abs(a) < Math.abs(b)) {
                    if (lastSequenceValueIsPrime(a, b)) {
                        k = primeCount(a, b, 1);
                        if (r < k) {
                            r = k;
                            System.out.println(a*b);
                        }
                    }
                }
            }
        }
    }

    private static long primeCount(long a, long b, long c) {
        while (CustomMathFunctions.isPrime(Math.abs(pow(c) + a * c++ + b))) ;
        return c;
    }

    public static boolean lastSequenceValueIsPrime(long a,long b) {
        return CustomMathFunctions.isPrime(Math.abs(pow(a - 2) + a * (a - 2) + b));
    }

    public static long pow(long m) {
        return m * m;
    }
}
