package project_euler.roblems_1_100.problem_73;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.Primes;

import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by whoosh on 3/1/16.
 */
public class CountingFractionInARange {

    private static final int N = 12000;
    private static TreeSet<Integer> primes = Primes
            .getPrimes(N).boxed().collect(Collectors.toCollection(TreeSet::new));

    public static void main(String[] args) {
        long result = 0;
        for (int d = 1; d <= N; d++) {
            if (primes.contains(d)) {
                int n = d - 1;
                for (int c = n; c > 0; c--) {
                    if (isBetween(c, d)) {
                        n = c;
                        break;
                    }
                }
                while (n > 0 && isBetween(n, d)) {
                    n--;
                    result++;
                }
            } else {
                int n = d - 1;
                for (int c = n; c > 0; c--) {
                    if (isBetween(c, d)) {
                        n = c;
                        break;
                    }
                }
                while (n > 0 && isBetween(n, d)) {
                    if (CustomMathFunctions.gcd(n, d) == 1) {
                        result++;
                    }
                    n--;
                }
            }
            System.out.println(d);
        }
        System.out.println(result);
    }


    public static boolean isBetween(int n1, int d1) {
        if (n1 == 1 && (d1 == 2 || d1 == 3)) return false;
        return d1 > 2 * n1 && d1 < 3 * n1;
    }
}
