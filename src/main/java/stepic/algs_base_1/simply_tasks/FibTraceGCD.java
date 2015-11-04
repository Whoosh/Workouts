package stepic.algs_base_1.simply_tasks;

/**
 * Created by whoosh on 11/4/15.
 */

public class FibTraceGCD {

    public static void main(String[] args) {
        long a = 0, b = 1, c;
        for (long i = 0; i < 70; i++) {
            System.out.println(a + " " + b + " " + gcd(a, b));
            c = b;
            b = a + b;
            a = c;
        }
    }

    public static long gcd(long a, long b) {
        if (a == 0) return b;
        if (b == 0) return a;
        return a < b ? gcd(b % a, a) : gcd(a % b, b);
    }
}
