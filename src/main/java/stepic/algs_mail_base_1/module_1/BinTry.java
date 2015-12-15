package stepic.algs_mail_base_1.module_1;

import java.io.IOException;

/**
 * Created by whoosh on 12/15/15.
 */
public class BinTry {


    public static void main(String[] args) throws IOException {

        int sortedArraySize = nextInt();
        int[] a = new int[sortedArraySize];
        for (int i = 0; i < sortedArraySize; i++) a[i] = nextInt();
        int bSize = nextInt();
        int[] b = new int[bSize];
        for (int i = 0; i < bSize; i++) b[i] = nextInt();
        for (int v : b) {
            System.out.print(bSearch(a, v) + " ");
        }
    }

    private static int bSearch(int[] map, int e) {
        int low = 0;
        int high = map.length - 1;
        int m;
        while (low <= high) {
            m = low + (high - low) / 2;
            if (map[m] == e) return m;
            if (m + 1 == map.length) return m;
            if (map[m] < e && map[m + 1] > e) {
                if (Math.abs(e - map[m + 1]) < Math.abs(e - map[m])) {
                    return m + 1;
                } else {
                    return m;
                }
            }
            if (map[m] >= e) {
                high = m - 1;
            } else {
                low = m + 1;
            }
        }
        return high + 1;
    }


    private static int nextInt() throws IOException {
        int d;
        int val = 0;
        while ((d = System.in.read()) == ' ') ;
        boolean l = false;
        if (d == '-') {
            l = true;
            d = System.in.read();
        }
        do {
            val += d - 48;
            if ((d = System.in.read()) < 48 || d > 57) break;
            val *= 10;
        } while (true);
        return l ? val * -1 : val;
    }
}
