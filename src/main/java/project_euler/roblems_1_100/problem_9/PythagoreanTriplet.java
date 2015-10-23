package project_euler.roblems_1_100.problem_9;

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a2 + b2 = c2
 * For example, 32 + 42 = 9 + 16 = 25 = 52.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 * https://projecteuler.net/problem=9
 */
public class PythagoreanTriplet {

    public static final long PROBLEM_NUMBER = 1000;
    public static final long N = PROBLEM_NUMBER / 2;

    public static void main(String[] args) {
        for (int a = 0; a < N; a++) {
            for (int b = a; b < N; b++) {
                for (int c = b; c < N; c++) {
                    if (isSum(a, b, c) && isPowEquality(a, b, c)) {
                        System.out.println(a + " " + b + " " + c);
                        System.out.println(a * b * c);
                        return;
                    }
                }
            }
        }
    }

    public static boolean isSum(long a, long b, long c) {
        return a + b + c == PROBLEM_NUMBER;
    }

    public static boolean isPowEquality(long a, long b, long c) {
        return tPow(a) + tPow(b) == tPow(c);
    }

    public static long tPow(long v) {
        return v * v;
    }
}
