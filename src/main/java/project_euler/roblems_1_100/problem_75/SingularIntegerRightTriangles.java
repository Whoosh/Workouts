package project_euler.roblems_1_100.problem_75;

import project_euler.sub_code.CustomMathFunctions;

import java.util.stream.IntStream;

/**
 * Created by whoosh on 4/1/16.
 */
public class SingularIntegerRightTriangles {
    public static void main(String[] args) {
        int N = 1500000;
        long a, b, c, w = 0;
        int[] map = new int[N + 1];
        for (long m = 2; m < Math.sqrt(N / 2); m++) {
            for (long n = 1; n < m; n++, w = 0) {
                if (((n + m) & 1) == 1 && CustomMathFunctions.gcdL(m, n) == 1) {
                    a = pow(m) - pow(n);
                    b = 2 * m * n;
                    c = pow(m) + pow(n);
                    while ((w += a + b + c) <= N) map[(int) (w)]++;
                }
            }
        }
        System.out.println(IntStream.of(map).filter(x -> x == 1).count());
    }

    public static long pow(long v) {
        return v * v;
    }

}
