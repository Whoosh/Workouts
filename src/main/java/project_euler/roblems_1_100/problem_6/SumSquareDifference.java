package project_euler.roblems_1_100.problem_6;

import java.util.stream.LongStream;

/**
 * The sum of the squares of the first ten natural numbers is,
 * 12 + 22 + ... + 102 = 385
 * The square of the sum of the first ten natural numbers is,
 * (1 + 2 + ... + 10)2 = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 * https://projecteuler.net/problem=6
 */
public class SumSquareDifference {

    private static final long START = 1;
    private static final long END = 100;

    public static void main(String[] args) {
        long sum1 = LongStream.rangeClosed(START, END).map(x -> x * x).sum();
        long sum2 = LongStream.rangeClosed(START, END).sum();
        System.out.println(sum2 * sum2 - sum1);
    }
}
