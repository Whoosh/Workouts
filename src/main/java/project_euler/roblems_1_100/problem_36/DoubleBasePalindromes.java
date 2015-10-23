package project_euler.roblems_1_100.problem_36;

import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=36
 */

public class DoubleBasePalindromes {

    public static final int PROBLEM = 100000000;

    public static void main(String[] args) {
        System.out.println(IntStream
                .rangeClosed(1, PROBLEM)
                .filter(DoubleBasePalindromes::isNormalBase)
                .filter(x -> isPalindrome(getBinaryArray(x)))
                .sum());
    }

    private static int[] getBinaryArray(int val) {
        int[] b = new int[sizeOf(val)];
        for (int i = 0; i < b.length; i++) b[i] = (val >> i) & 1;
        return b;
    }

    private static boolean isNormalBase(int val) {
        int r = 0;
        for (int i = val; i != 0; i /= 10) r = 10 * r + i % 10;
        return val == r;
    }

    private static int sizeOf(int val) {
        int size = 0;
        for (int i = val; i != 0; i /= 2) size++;
        return size;
    }

    private static boolean isPalindrome(int[] val) {
        for (int i = 0; i < val.length / 2; i++)
            if (val[i] != val[val.length - i - 1]) return false;
        return true;
    }
}
