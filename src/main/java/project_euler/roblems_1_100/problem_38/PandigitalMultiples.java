package project_euler.roblems_1_100.problem_38;

import project_euler.sub_code.CustomMathFunctions;

import java.util.HashSet;

/**
 * https://projecteuler.net/problem=38
 */
public class PandigitalMultiples {

    public static final int PAN_LIMIT = 9;
    public static HashSet<Integer> buffer = new HashSet<>();

    public static void main(String[] args) {
        Integer[] ijBuffer;
        for (int i = 9999; i > 0; i--) {
            for (int j = 2; j < 5; j++) {
                if (isPan(i, i * j)) {
                    System.out.println(i + " " + i * 2);
                }
            }
        }
    }

    private static boolean isPan(Integer... products) {
        buffer.clear();
        int size = 0;
        for (Integer product : products) {
            size += CustomMathFunctions.valLen(product);
            if (size > PAN_LIMIT) return false;
        }
        for (int product : products) {
            setInBuffer(product);
        }
        return buffer.size() == PAN_LIMIT && !buffer.contains(0);
    }

    private static void setInBuffer(int val) {
        for (int i = val; i != 0; i /= 10) buffer.add(i % 10);
    }
}
