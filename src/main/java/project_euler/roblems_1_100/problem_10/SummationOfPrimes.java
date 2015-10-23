package project_euler.roblems_1_100.problem_10;


import project_euler.sub_code.CustomMathFunctions;

import java.util.stream.LongStream;

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 * https://projecteuler.net/problem=10
 */
public class SummationOfPrimes {
    public static void main(String[] args) {
        System.out.println(LongStream.rangeClosed(2, 2000000).filter(CustomMathFunctions::isPrime).sum());
    }
}
