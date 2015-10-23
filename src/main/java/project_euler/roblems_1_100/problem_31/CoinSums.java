package project_euler.roblems_1_100.problem_31;

/**
 * https://projecteuler.net/problem=31
 */

public class CoinSums {

    private static final int MONEY = 200;
    private static final int[] p = {200, 100, 50, 20, 10, 5, 2};

    public static void main(String[] args) {
        System.out.println(getCount(MONEY, 0, 0));
    }

    public static int getCount(int val, int index, int count) {
        if (index == p.length) return count;
        for (int buffer = val; buffer >= 0; buffer -= p[index]) {
            if (index == p.length - 1) count++;
            count += getCount(buffer, index + 1, 0);
        }
        return count;
    }
}
