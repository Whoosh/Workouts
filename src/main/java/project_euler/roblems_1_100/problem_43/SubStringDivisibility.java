package project_euler.roblems_1_100.problem_43;

import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

import java.util.HashSet;
import java.util.Set;

/**
 * https://projecteuler.net/problem=43
 */
public class SubStringDivisibility implements Starter {

    private static final int[] SEED = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final int[] TEMPLATE = {0, 0, 2, 3, 5, 7, 11, 13, 17};
    private static Set<String> pandigitals = new HashSet<>();

    public static void main(String[] args) {
        permutation(SEED.length - 1);
        new SubStringDivisibility().start();
    }

    @StartWithLazzyBenchmark
    public void run() {
        System.out.println(
                pandigitals
                .stream()
                .filter(SubStringDivisibility::hasPrimeDivisibleProperty)
                .unordered()
                .parallel()
                .mapToLong(Long::valueOf)
                .sum());
    }

    private static boolean hasPrimeDivisibleProperty(String val) {
        for (int i = 2; i < TEMPLATE.length; i++)
            if (Long.valueOf(val.substring(i - 1, i + 2)) % TEMPLATE[i] != 0)
                return false;
        return true;
    }

    private static void permutation(int length) {
        if (length == 0) return;
        for (int i = 0; i <= length; i++) {
            permutation(length - 1);
            mixSeed((length & 1) > 0 ? 0 : i, length);
            pandigitals.add(makeStringFor(SEED));
        }
    }

    private static String makeStringFor(int[] seed) {
        final StringBuilder builder = new StringBuilder(SEED.length);
        for (int i : seed) builder.append(i);
        return String.valueOf(builder);
    }

    private static void mixSeed(int i, int j) {
        int a = SEED[i];
        SEED[i] = SEED[j];
        SEED[j] = a;
    }
}
