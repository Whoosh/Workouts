package project_euler.roblems_1_100.problem_48;

import java.math.BigInteger;

/**
 * https://projecteuler.net/problem=48
 */
public class Powers {
    public static void main(String[] args) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 1; i <= 1000; i++)
            result = result.add(BigInteger.valueOf(i).pow(i));
        String s = result.toString();
        System.out.println(s.substring(s.length() - 10, s.length()));
    }
}
