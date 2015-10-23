package project_euler.roblems_1_100.problem_41;

import project_euler.sub_code.CustomMathFunctions;

import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=41
 */
public class PandigitalPrime {

    final static HashSet<Integer> checkedBuffer = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(IntStream.iterate(7654321, x -> x - 2).filter(p -> isPandigital(p) && CustomMathFunctions.isPrime(p)).findFirst());
    }

    private static boolean isPandigital(int num) {
        final int len = CustomMathFunctions.valLen(num);
        checkedBuffer.clear();
        for (int v : CustomMathFunctions.numberToArray(num)) checkedBuffer.add(v);
        if (checkedBuffer.size() != len) return false;
        for (int i = 1; i <= len; i++) {
            if (!checkedBuffer.contains(i))
                return false;
        }
        return true;
    }
}
