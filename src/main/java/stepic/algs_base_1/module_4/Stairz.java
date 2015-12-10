package stepic.algs_base_1.module_4;

import java.io.IOException;

/**
 * Created by whoosh on 12/10/15.
 */

public class Stairz {

    public static void main(String[] args) throws IOException {
        int count = nextInt();
        int[] stairs = new int[count];
        for (int i = 0; i < count; i++) stairs[i] = nextInt();
        if (count == 1) {
            System.out.println(stairs[0]);
            return;
        }
        stairs[stairs.length - 2] += stairs[stairs.length - 1];
        for (int i = stairs.length - 3; i >= 0; --i) {
            stairs[i] = Math.max(stairs[i] + stairs[i + 1], stairs[i] + stairs[i + 2]);
        }
        System.out.println(Math.max(stairs[0],stairs[1]));
    }


    private static int nextInt() throws IOException {
        int d;
        int val = 0;
        while ((d = System.in.read()) == ' ') ;
        boolean sign = false;
        do {
            if (d == '-') {
                sign = true;
                continue;
            }
            val += d - 48;
            val *= 10;
        } while ((d = System.in.read()) > 47 && d < 58);
        if (sign) val *= -1;
        return val / 10;
    }
}
