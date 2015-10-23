package project_euler.roblems_1_100.problem_52;

import project_euler.sub_code.CustomMathFunctions;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=52
 */
public class PermutedMultiplies {

    public static void main(String[] args) {
        System.out.println(IntStream
                .iterate(2, x -> ++x)
                .filter(x->isPermutation(x,2))
                .filter(x->isPermutation(x,3))
                .findFirst());
    }

    private static boolean isPermutation(int x, int n) {
        final Integer[] digits = CustomMathFunctions.numberToArray(x);
        final Integer[] modifyDigits = CustomMathFunctions.numberToArray(x*n);
        Arrays.sort(digits);
        Arrays.sort(modifyDigits);
        return Arrays.hashCode(digits) == Arrays.hashCode(modifyDigits);
    }
}
