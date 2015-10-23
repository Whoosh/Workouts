package project_euler.roblems_1_100.problem_15;

import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

/**
 * https://projecteuler.net/problem=15
 */

public class LatticePaths implements Starter {

    private static final int N = 20;

    public static void main(String[] args) {
        new LatticePaths().start();
    }

    @StartWithLazzyBenchmark
    public void calculate() {
        long[][] buffered = new long[2][N];
        init(buffered);
        for (int h = 0; h < N - 1; h++, buffered[1][0]++) {
            for (int j = 1; j < N; j++)
                buffered[1][j] = buffered[0][j] + buffered[1][j - 1];
            System.arraycopy(buffered[1], 0, buffered[0], 0, N);
        }
        System.out.println(buffered[1][N - 1]);
    }

    private void init(long[][] buffered) {
        buffered[1][0] = 3;
        for (int j = 0; j < N; j++) buffered[0][j] = j + 2;
    }
}
