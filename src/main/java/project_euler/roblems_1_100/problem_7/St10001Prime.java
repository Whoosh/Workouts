package project_euler.roblems_1_100.problem_7;

import project_euler.sub_code.CustomMathFunctions;

import java.util.function.LongPredicate;
import java.util.stream.LongStream;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 * https://projecteuler.net/problem=7
 */
public class St10001Prime {

    public static final long PROBLEM_NUMBER = 10001L;

    private static final LongPredicate PREDICATE = new LongPredicate() {
        private long counter;

        @Override
        public boolean test(long aLong) {
            if (CustomMathFunctions.isPrime(aLong)) {
                ++counter;
            }
            return counter == PROBLEM_NUMBER;
        }
    };

    public static void main(String[] args) {
        System.out.println(LongStream.iterate(2, x -> x + 1).filter(PREDICATE).findFirst());
    }
}
