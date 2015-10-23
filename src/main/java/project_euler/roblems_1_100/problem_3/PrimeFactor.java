package project_euler.roblems_1_100.problem_3;

import project_euler.sub_code.CustomMathFunctions;

import java.util.Stack;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/*
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 * https://projecteuler.net/problem=3
 */

public class PrimeFactor {

    private static final long PROBLEM_NUMBER = 600851475143L;
    private static final Stack<Long> results = new Stack<>();
    private static final LongPredicate predicate = x -> (((x & 1) > 0) && (PROBLEM_NUMBER % x == 0) && CustomMathFunctions.isPrime(x));

    public static void main(String[] args) {
        results.add(finder(2, PROBLEM_NUMBER / 2));
        while (results.peek() != 0) results.add(finder(results.peek() + 1, PROBLEM_NUMBER / results.peek()));
        System.out.println(results.get(results.size() - 2));
    }

    public static long finder(long start, long end) {
        try {
            return LongStream.range(start, end).filter(predicate).findFirst().getAsLong();
        } catch (Exception ex) {
            return 0;
        }
    }
}

