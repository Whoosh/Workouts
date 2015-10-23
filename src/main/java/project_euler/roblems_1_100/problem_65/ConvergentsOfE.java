package project_euler.roblems_1_100.problem_65;

import project_euler.sub_code.CustomMathFunctions;

import java.math.BigInteger;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.valueOf;

/**
 * https://projecteuler.net/problem=65.
 * Nk = Ak*Nk-1+Nk-2 // тоже думал над последовательностью только 1 части дроби, опять гуглил, нелюблю дроби -_-
 */

public class ConvergentsOfE {

    public static void main(String[] args) {
        BigInteger d = ONE, n = valueOf(2), nM1 = d;
        for (int i = 2; i <= 100; i++, nM1 = d) {
            d = n;
            n = n.multiply(valueOf((i % 3 == 0) ? 2 * (i / 3) : 1)).add(nM1);
        }
        System.out.println(CustomMathFunctions.sumOfDigits(n));
    }
}
