package stepic.algs_mail_base_1.module_3;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by whoosh on 1/24/16.
 */
public class ColorLines {

    public static void main(String[] args) throws IOException {
        int count = nextInt();
        Point[] points = new Point[count * 2];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(nextInt(), 1);
            points[++i] = new Point(nextInt(), -1);
        }
        Arrays.sort(points, (x, y) -> {
            if (x.p - y.p == 0) return -x.v;
            return x.p - y.p;
        });

        int result = 0;
        int s = points[0].v;
        Point prev = points[0];
        for (int i = 1; i < points.length; i++) {
            s += points[i].v;
            points[i].v = s;
            if (prev.v == 1 && prev.p != points[i].p) result += points[i].p - prev.p;
            prev = points[i];
        }
        System.out.println(result);
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

    private static class Point {
        int p;
        int v;

        public Point(int p, int v) {
            this.p = p;
            this.v = v;
        }
    }
}
