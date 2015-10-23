package project_euler.roblems_1_100.problem_24;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
 * If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
 * The lexicographic permutations of 0, 1 and 2 are:
 * 012   021   102   120   201   210
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 * https://projecteuler.net/problem=24
 */

public class LexicographicPermutations {

    private static final int[] SEED = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int PROBLEM = 1000000;
    private static TreeSet<String> results = new TreeSet<>();

    public static void main(String[] args) {
        permutation(SEED.length - 1);
        System.out.println(results.stream().skip(PROBLEM - 1).findFirst());
    }

    private static void permutation(int length) {
        if (length == 0) return;
        for (int i = 0; i <= length; i++) {
            permutation(length - 1);
            mixSeed((length & 1) > 0 ? 0 : i, length);
            results.add(Arrays.toString(SEED));
        }
    }

    private static void mixSeed(int i, int j) {
        int a = SEED[i];
        SEED[i] = SEED[j];
        SEED[j] = a;
    }
}
