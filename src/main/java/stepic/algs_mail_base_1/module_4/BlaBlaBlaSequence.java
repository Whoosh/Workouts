package stepic.algs_mail_base_1.module_4;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by whoosh on 1/24/16.
 */
public class BlaBlaBlaSequence {
    public static void main(String[] args) throws IOException {
        int count = nextInt();
        int k = 0;
        Point[] points = new Point[count];
        for (int i = 0; i < count; i++) {
            points[i] = new Point(i, nextInt());
        }
        HashMap<String,String> s = new HashMap<>();
        s.entrySet();
        k = nextInt();
        final int finalK = k;
        Arrays.sort(points, (x, y) -> {
            if (y.index >= x.index + finalK) {
                int b = x.index;
                x.index = y.index;
                y.index = b;
                return 1;
            }
            return x.value-y.value;
        });
        for (int i = 0; i < points.length; i++) {
            System.out.print(points[i].value);
            System.out.print(' ');
        }
    }

    public static class Point {
        int index;
        int value;

        Point(int index, int value) {
            this.index = index;
            this.value = value;
        }
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
