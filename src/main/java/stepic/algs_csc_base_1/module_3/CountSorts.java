package stepic.algs_csc_base_1.module_3;

import java.io.IOException;

/**
 * Created by whoosh on 11/25/15.
 */

/*
* Easy sample
* 10
* 2 3 9 2 9 5 4 3 2 1
* */

public class CountSorts {

    public static void main(String[] args) throws IOException {
        int[] map = new int[10];
        int[] values = new int[nextInt()];
        for (int i = 0; i < values.length; i++) values[i] = nextInt();
        for (int value : values) map[value]++;
        int index = values.length - 1;
        for (int i = map.length - 1; i >= 0; --i) {
            if (map[i] != 0) {
                for (int j = map[i]; j > 0; j--) values[index--] = i;
            }
        }
        for (int value : values) {
            System.out.print(value);
            System.out.print(' ');
        }
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
