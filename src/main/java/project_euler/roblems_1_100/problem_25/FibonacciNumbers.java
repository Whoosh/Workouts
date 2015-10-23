package project_euler.roblems_1_100.problem_25;

import project_euler.sub_code.TakeWhile;

import java.math.BigInteger;
import java.util.stream.Stream;

/**
 * https://projecteuler.net/problem=25
 */

public class FibonacciNumbers {

    private static final int PROBLEM = 1000;

    public static void main(String[] args) {
        System.out.println(
                TakeWhile.takeWhileOrdered(
                        Stream.iterate(
                                new BigInteger[]{BigInteger.ONE, BigInteger.ONE},
                                x -> new BigInteger[]{x[1], x[0].add(x[1])}),
                        x -> x[0].toString().length() != PROBLEM).count() + 1);
    }
}
