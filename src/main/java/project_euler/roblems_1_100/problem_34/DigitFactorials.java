package project_euler.roblems_1_100.problem_34;

import java.util.ArrayList;

/**
 * https://projecteuler.net/problem=34
 */
public class DigitFactorials {

    private static final int PROBLEM_LIMIT = 362880; // 9!

    private static ArrayList<Integer> factorials = new ArrayList<>();

    public static void main(String[] args) {
        storeFactorials();
        int result = 0;
        for (int i = 3; i < PROBLEM_LIMIT; i++) {
            result += getSumIfPossibly(i);
        }
        System.out.println(result);
    }

    private static int getSumIfPossibly(int value) {
        int result = 0;
        for (int i = value; i != 0; i /= 10) {
            result += factorials.get(i % 10);
        }
        return result == value ? value : 0;
    }

    private static void storeFactorials() {
        int result;
        do {
            result = 1;
            for (int i = 1; i < factorials.size(); i++)
                result *= i;
            factorials.add(result);
        } while (result < PROBLEM_LIMIT);
        factorials.remove(0);
    }
}
