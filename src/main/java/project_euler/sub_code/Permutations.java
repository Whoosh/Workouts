package project_euler.sub_code;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static project_euler.sub_code.CustomMathFunctions.numberToArray;

public class Permutations {

    public static Set<Integer> getPermutations(int val) {
        Integer[] seed = numberToArray(val);
        return permutation(new HashSet<>(), seed, seed.length - 1)
                .stream()
                .map(CustomMathFunctions::toValue)
                .collect(Collectors.toSet());
    }

    private static <T, C extends Collection<T[]>> C permutation(C permutations, T[] seed, int length) {
        if (length == 0) return permutations;
        for (int i = 0; i <= length; i++) {
            permutation(permutations, seed, length - 1);
            mixSeed(seed, (length & 1) > 0 ? 0 : i, length);
            permutations.add(Arrays.copyOf(seed, seed.length));
        }
        return permutations;
    }

    private static <T> void mixSeed(T[] seed, int i, int j) {
        T a = seed[i];
        seed[i] = seed[j];
        seed[j] = a;
    }
}
