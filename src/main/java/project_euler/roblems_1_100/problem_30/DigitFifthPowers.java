package project_euler.roblems_1_100.problem_30;

/**
 * https://projecteuler.net/problem=30
 */
public class DigitFifthPowers {

    private final static int START_PROBLEM = 2;
    private final static int END_PROBLEM = 200000;
    private final static int POWER = 5;

    public static void main(String[] args) {
        long result = 0;
        for (long i = START_PROBLEM; i < END_PROBLEM; i++) {
            if (canBeWrite(i)) result += i;
        }
        System.out.println(result);
    }

    public static boolean canBeWrite(long val) {
        final byte[] bytes = String.valueOf(val).getBytes();
        final long seed = val;
        val = 0;
        for (byte aByte : bytes) val += Math.pow(aByte - 48, POWER);
        return val == seed;
    }
}
