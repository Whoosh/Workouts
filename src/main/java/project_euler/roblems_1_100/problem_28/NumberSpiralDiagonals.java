package project_euler.roblems_1_100.problem_28;

/**
 * https://projecteuler.net/problem=28
 */
public class NumberSpiralDiagonals {

    private final static long MATRIX_SIZE = 500; // 1/4 -1

    public static void main(String[] args) {
        System.out.println(findSum(MATRIX_SIZE));
    }

    public static long findSum(long n) {
        long result = 0;
        for (long step = 3, size = 0, prod = 9; size++ < n; step += 2, prod = step * step)
            result += prod * 4 - (step - 1) * 6;
        return result + 1;
    }
}
