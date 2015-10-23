package project_euler.roblems_1_100.problem_5;

import java.util.stream.LongStream;

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 * https://projecteuler.net/problem=5
 */
public class SmallestMultiple {

    private final static long START = 1;
    private final static long END = 20;

    public static void main(String[] args) {
        System.out.println(LongStream.iterate(END, x -> x + 1).filter(x -> LongStream.rangeClosed(START, END).parallel().allMatch(y -> x % y == 0)).findFirst());
    }
}
