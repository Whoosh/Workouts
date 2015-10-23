package project_euler.roblems_1_100.problem_37;


import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

/**
 * https://projecteuler.net/problem=37
 */
public class TruncatablePrimes implements Starter {

    public static final int PROBLEM = 11;

    public static void main(String[] args) {
        new TruncatablePrimes().start();
    }

    @StartWithLazzyBenchmark
    public void run() {
        int result = 0;
        for (int i = 11, counter = 0; counter != PROBLEM; i += 2) {
            if (isTruncatable(i)) {
                result += i;
                counter++;
            }
        }
        System.out.println("Result - " + result);
    }

    private static boolean isTruncatable(int val) {
        int len, digit, valLen = CustomMathFunctions.valLen(val);
        for (int i = val; i != 0; i /= 10) {
            if (!CustomMathFunctions.isPrime(i)) return false;
        }
        for (int j = valLen; j > 0; j--) {
            len = (int) Math.pow(10, j - 1);
            digit = val / len;
            if (!CustomMathFunctions.isPrime(val)) return false;
            val = Math.abs(val - digit * len);
        }
        return true;
    }
}
