package stepic.algs_mail_base_1.module_1;

import java.io.IOException;

/**
 * Created by whoosh on 12/15/15.
 */
public class DoubleSearch {

    public static void main(String[] args) throws IOException {

        int size = nextInt();
        int[] a = new int[size];
        int[] b = new int[size];

        for (int i = 0; i < size; i++) {
            a[i] = nextInt();
        }
        for (int i = 0; i < size; i++) {
            b[i] = nextInt();
        }

        int sum = Integer.MIN_VALUE;
        int a_i = 0;
        int b_i = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (sum < a[i] + b[j]) {
                    sum = a[i] + b[j];
                    a_i = i;
                    b_i = j;
                }
            }
        }
        System.out.println(a_i + " " + b_i);
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
