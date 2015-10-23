package project_euler.sub_code;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class CustomMathFunctions implements Starter {

    public static boolean isPrime(long n) {
        return n > 1 && !LongStream.rangeClosed(2, (long) Math.sqrt(n)).anyMatch(x -> n % x == 0);
    }

    public static BigInteger factorial(BigInteger val) {
        return val.equals(BigInteger.ONE) ? val : val.multiply(factorial(val.subtract(BigInteger.ONE)));
    }

    public static Integer[] numberToArray(Integer val) {
        return longToArray(Long.valueOf(val));
    }

    public static Integer[] longToArray(long val) {
        long k;
        Integer[] number = new Integer[valLen(val) + 1];
        for (int j = number.length; j > 0; j--) {
            k = val % 10;
            number[j - 1] = (int) k;
            val = val / 10;
        }
        return number;
    }

    public static int[] numberToArray(int val, int length) {
        int k, len;
        int[] number = new int[length];
        for (int j = length; j > 0; j--) {
            len = (int) Math.pow(10, j - 1);
            k = val / len;
            val = Math.abs(val - k * len);
            number[length - j] = k;
        }
        return number;
    }

    public static int valLen(long val) {
        return (int) Math.log10(val);
    }

    public static int factorial(int val) {
        return val == 1 ? val : val * factorial(val - 1);
    }

    public static long sumOfDigits(byte[] charDigits) {
        long result = 0;
        for (byte charDigit : charDigits) result += charDigit;
        return result - charDigits.length * 48;
    }

    public static long sumOfDigits(BigInteger val) {
        return sumOfDigits(val.toString().getBytes());
    }

    public static long log10(BigInteger val) {
        return val.toString().length();
    }

    public static long alphanumericSumOf(String val) {
        long result = 0;
        for (byte charDigit : val.getBytes()) result += charDigit;
        return result - val.length() * 64;
    }

    public static long sumDividersOf(long number) {
        return LongStream.rangeClosed(1, number / 2).filter(x -> number % x == 0).sum();
    }

    public static long countHunkyDividersOf(long n) {
        if (n <= 1) return 0;
        else if (n == 2) return 1;
        final long res = TakeWhile.longTakeWhileOrdered(
                LongStream.rangeClosed(1, n / 2).filter(x -> n % x == 0), x -> x < n / x).count() * 2;
        return n % Math.sqrt(n) == 0 ? res : res - 1;
    }

    public static Integer toValue(Integer[] seed) {
        int result = 0;
        for (int a : seed) result = result * 10 + a;
        return result;
    }

    public static Long toLongValue(Integer[] seed) {
        long result = 0;
        for (long a : seed) result = result * 10 + a;
        return result;
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
