package project_euler.roblems_1_100.problem_17;

import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=17
 */

public class NumberLetter {

    private final static int one[] = {0, 3, 3, 5, 4, 4, 3, 5, 5, 4, 3, 6, 6, 8, 8, 7, 7, 9, 8, 8};
    private final static int ten[] = {0, 0, 6, 6, 5, 5, 5, 7, 6, 6};
    private static final int AND = 3;
    private static final int HUNDRED = 7;
    private static final int THOUSAND = 8;

    public static void main(String[] args) {
        System.out.println(LongStream.rangeClosed(1, 1000).map(x -> letterLengthForNumber((int) x)).sum());
    }

    private static long letterLengthForNumber(int n) {
        int add = 0;
        if (n >= 1000) {
            add += one[n / 1000] + THOUSAND;
            n = n - (n / 1000) * 1000;
        }
        if (n >= 100) {
            add += one[n / 100] + HUNDRED;
            n = n - (n / 100) * 100;
            if (n > 0) add += AND;
        }
        return last(n, add);
    }

    private static int last(int n, int add) {
        if (n > 19) {
            add += ten[n / 10] + one[n % 10];
        } else {
            add += one[n];
        }
        return add;
    }
}
