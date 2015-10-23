package project_euler.roblems_1_100.problem_64;

import static java.lang.Math.sqrt;

/**
 * https://projecteuler.net/problem=64
 * http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion
 * http://www.mathblog.dk/project-euler-continued-fractions-odd-period/
 * // просто упоротая постановка задачи или я слоупок, подсмотрел -_-
 */
public class OddPeriodSquareRoots {

    private static final int N = 10000;

    public static void main(String[] args) {
        int result = 0;
        for (int i = 2, s = 1, m = 0, a = 1, d = 1, p; i < N; i++, s = (int) sqrt(i), a = s, d = 1, m = 0) {
            if (s * s == i) continue;
            for (p = 0; a != s * 2; p++, m = d * a - m)
                a = (s + m) / (d = (i - m * m) / d);
            if ((p & 1) == 1) result++;
        }
        System.out.println(result);
    }
}
