package project_euler.roblems_1_100.problem_16;

import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=16
 */

public class PowerDigitSum {
    private final static int E = 1000;

    public static void main(String[] args) {
        BigInteger result = BigInteger.valueOf(0);
        byte[] bytes = new BigInteger("2").pow(E).toString().getBytes();
        for (byte b : bytes) result = result.add(BigInteger.valueOf(b));
        System.out.println((BigInteger.valueOf(48 * bytes.length).subtract(result)).abs());
    }
}
