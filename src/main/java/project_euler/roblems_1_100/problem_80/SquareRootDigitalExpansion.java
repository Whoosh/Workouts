package project_euler.roblems_1_100.problem_80;

import project_euler.sub_code.CustomMathFunctions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * Created by whoosh on 6/20/16.
 */

public class SquareRootDigitalExpansion {

    private static final BigDecimal SQRT_DIG = new BigDecimal(100);
    private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

    public static void main(String[] args) {
        System.out.println(sumOfDigits(bigSqrt(new BigDecimal(2))));
        long result = 0;
        for (int i = 1; i <= 100; i++) {
            result += sumOfDigits(bigSqrt(BigDecimal.valueOf(i)));
        }
        System.out.println(result);
    }

    private static long sumOfDigits(BigDecimal d) {
        String[] split = d.toString().split("[.]");
        if (CustomMathFunctions.sumOfDigits(new BigInteger(split[1].substring(0, 100))) == 0) {
            return 0;
        }
        return CustomMathFunctions.sumOfDigits(new BigInteger(split[1].substring(0, 100 - split[0].length()))) + Integer.valueOf(split[0]);
    }


    private static BigDecimal sqrtNewton(BigDecimal c, BigDecimal xn, BigDecimal precision) {
        BigDecimal fx = xn.pow(2).add(c.negate());
        BigDecimal fpx = xn.multiply(new BigDecimal(2));
        BigDecimal xn1 = fx.divide(fpx, 2 * SQRT_DIG.intValue(), RoundingMode.HALF_DOWN);
        xn1 = xn.add(xn1.negate());
        BigDecimal currentSquare = xn1.pow(2);
        BigDecimal currentPrecision = currentSquare.subtract(c);
        currentPrecision = currentPrecision.abs();
        if (currentPrecision.compareTo(precision) <= -1) {
            return xn1;
        }
        return sqrtNewton(c, xn1, precision);
    }

    public static BigDecimal bigSqrt(BigDecimal c) {
        return sqrtNewton(c, new BigDecimal(1), new BigDecimal(1).divide(SQRT_PRE));
    }
}
