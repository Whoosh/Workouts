package project_euler.roblems_1_100.problem_63;

import static java.lang.Math.ceil;
import static java.lang.Math.pow;

/**
 * https://projecteuler.net/problem=63
 */

public class PowerfulDigit {

    public static void main(String[] args) {
        int result = -1;
        for (int i = 1, n = 0; n < 10; i++, n = (int) ceil(pow(10, (i - 1.0) / i))) result += 10 - n;
        System.out.println(result);
    }
}
