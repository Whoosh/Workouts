package project_euler.roblems_1_100.problem_73;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.Primes;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * https://projecteuler.net/problem=73
 */
public class CountingFractionInARange {

    private static final int N = 12000;
    private static HashSet<Integer> primes = Primes
            .getPrimes(N).boxed().collect(Collectors.toCollection(HashSet::new));

    public static void main(String[] args) {
        long result = 0;
        int n;
        for (int d = 1; d <= N; d++) {
            n = ((d & 1) == 0) ? d / 2 - 1 : d / 2;
            if (primes.contains(d)) {
                result += (n - d / 3);
            } else {
                while (d < 3 * n) {
                    if (primes.contains(n)) result++;
                    else if (CustomMathFunctions.gcd(n, d) == 1) result++;
                    n--;
                }
            }
        }
        System.out.println(result);
    }
}
