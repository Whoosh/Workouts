package project_euler.roblems_1_100.problem_69;

import project_euler.sub_code.EulerFunctions;
import project_euler.sub_code.Primes;

import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * https://projecteuler.net/problem=69
 */
public class ToientMaximum {

    private static double val = 0, max = 0;
    private static final int N = 1000000;
    private static EulerFunctions eulerFunctions = new EulerFunctions(
            Primes.getPrimes(N)
                    .boxed()
                    .collect(Collectors.toCollection(TreeSet::new)));

    public static void main(String[] args) {
        for (double n = 2, k; n <= N; n++) {
            k = n / eulerFunctions.totient((int) n);
            if (k > max) {
                max = k;
                val = n;
            }
        }
        System.out.println(max + " " + val);
    }
}
