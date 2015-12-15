package stepic.algs_csc_base_1.hard_tasks;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by whoosh on 11/2/15.
 */

public class ModOfLongFib {
    // fib(10^18) mod 10^5
    private static int mod;

    private static ArrayList<Integer> m;

    public static void main(String[] args) {
        normal();
    }

    private static void normal() {
        Scanner scanner = new Scanner(System.in);
        long v = scanner.nextLong();
        mod = (int) scanner.nextLong();
        m = new ArrayList<>(mod * 2);
        m.add(0);
        m.add(1);
        int a = 0;
        int b = 1;
        int c;
        while (!checkSeq(m)) {
            c = b % mod;
            b = (a + b) % mod;
            a = c;
            m.add(b);
        }
        System.out.print(m.get((int) (v % (m.size()))));
    }

    private static int fi(int val) {
        int a = 0;
        int b = 1;
        int c;
        while (--val > 1) {
            c = b % mod;
            b = (a + b) % mod;
            a = c;
        }
        return (a + b) % mod;
    }

    private static BigInteger fi(long val, int mod) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger c;
        ArrayList<Integer> buffer = new ArrayList<>(mod * 10);
        boolean seqFound = false;
        buffer.add(0);
        buffer.add(1);
        while (--val > 1 && !seqFound) {
            c = b.mod(BigInteger.valueOf(mod));
            b = a.add(b).mod(BigInteger.valueOf(mod));
            a = c;
            buffer.add(a.add(b).mod(BigInteger.valueOf(mod)).intValue());
            if (checkSeq(buffer)) {
                seqFound = true;
                break;
            }
        }
        if (seqFound) {
            return BigInteger.valueOf(buffer.get((int) (val % buffer.size() - 1)));
        }
        return a.add(b).mod(BigInteger.valueOf(mod));
    }

    private static boolean checkSeq(ArrayList<Integer> buffer) {
        int i = 0, end = buffer.size();
        if (end < 3) return false;
        int checks = 2;
        for (i = 0; i < checks; i++) {
            if (!buffer.get(i).equals(buffer.get(end - checks + i))) {
                return false;
            }
        }
        if (i == checks) {
            for (int h = 0; h < checks; h++) {
                buffer.remove(end - h - 1);
            }
            return true;
        }
        return false;
    }
}
