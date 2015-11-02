package stepic.algs_base_1.simply_tasks;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by whoosh on 11/2/15.
 */

public class BFib {
    public static void main(String[] args) throws IOException {
        System.out.println(fi(new Scanner(System.in).nextInt()));
        System.out.println(fi(1));
        System.out.println(fi2(1));
        for (int i = 0; i < 100; i++) {
            if (fi(i) != fi2(i)) {
                System.out.println(i + " " + fi(i) + " " + fi2(i));
            }
        }
    }

    private static int fi(int val) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger c;
        while (--val > 1) {
            c = b.mod(BigInteger.valueOf(10));
            b = a.add(b).mod(BigInteger.valueOf(10));
            a = c;
        }
        return a.add(b).mod(BigInteger.valueOf(10)).intValue();
    }

    private static int fi2(int val) {
        if (val == 0) return 0;
        int buf[] = new int[]{0, 1, 1, 2, 3, 5, 8, 3, 1, 4, 5, 9, 4, 3, 7, 0, 7, 7, 4, 1, 5, 6, 1, 7, 8, 5, 3, 8, 1, 9, 0, 9, 9, 8, 7, 5, 2, 7, 9, 6, 5, 1, 6, 7, 3, 0, 3, 3, 6, 9, 5, 4, 9, 3, 2, 5, 7, 2, 9, 1, 0, 1, 1};
        return buf[val % 60];
    }
}
