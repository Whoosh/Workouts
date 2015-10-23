package project_euler.roblems_1_100.problem_29;

import java.math.BigInteger;
import java.util.HashSet;

import static java.math.BigInteger.valueOf;

/**
 * https://projecteuler.net/problem=29
 */

public class DistinctPowers {

    private static final int PROBLEM = 100;

    public static void main(String[] args) {
        HashSet<BigInteger> results = new HashSet<>();
        for (int a = 2; a <= PROBLEM; a++) {
            for (int b = 2; b <= PROBLEM; b++) {
                results.add(valueOf(a).pow(b));
            }
        }
        System.out.println(results.size());
    }
}
