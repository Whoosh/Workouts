package stepic.algs_mail_base_1.module_4;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by whoosh on 1/25/16.
 */
public class Task {
    public static void main(String[] args) throws IOException {
        int count = (int) nextLong(System.in);
        long[] arr = new long[count];
        for (int i = 0; i < count; i++) {
            arr[i] = nextLong(System.in);
        }
        Arrays.sort(arr);
        for (int i1 = 0; i1 < arr.length - 1; i1++) {
            System.out.print(arr[i1]);
            System.out.print(' ');
        }
        System.out.print(arr[count - 1]);
    }

    private static long d;
    private static long val = 0;

    private static long nextLong(InputStream reader) throws IOException {
        val = 0;
        while ((d = reader.read()) == 32) ;
        boolean l = false;
        if (d == 45) {
            l = true;
            d = reader.read();
        }
        do {
            val += d - 48;
            if ((d = reader.read()) < 48 || d > 57) break;
            val *= 10;
        } while (true);
        return l ? -val : val;
    }
}
