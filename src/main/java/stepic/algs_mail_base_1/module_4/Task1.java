package stepic.algs_mail_base_1.module_4;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by whoosh on 1/24/16.
 */
public class Task1 {
    public static void main(String[] args) throws IOException {
        int[] bulk = new int[2*((int) Math.pow(10,6))];
        int count = 0;
        for (int i = 0; i < bulk.length; i++) {
            bulk[i] = nextInt(System.in);
            if (System.in.available() == 0) {
                count = i + 1;
                break;
            }
        }
        Arrays.sort(bulk, 0, count);
        for (int i = 9; i < count; i += 10) {
            System.out.print(bulk[i]);
            System.out.print(' ');
        }
    }

    private static int d;
    private static int val = 0;

    private static int nextInt(InputStream reader) throws IOException {
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
