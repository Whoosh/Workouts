package project_euler.roblems_1_100.problem_20;

import project_euler.sub_code.CustomMathFunctions;

import java.math.BigInteger;

/**
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 * Find the sum of the digits in the number 100!
 * <p>
 * https://projecteuler.net/problem=20
 */
public class FactorialDigitSum {

    public static void main(String[] args) {
        System.out.println(CustomMathFunctions.sumOfDigits(CustomMathFunctions.factorial(BigInteger.valueOf(100)).toString().getBytes()));
    }
}
