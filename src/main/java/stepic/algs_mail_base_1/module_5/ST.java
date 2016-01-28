package stepic.algs_mail_base_1.module_5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by whoosh on 1/27/16.
 */

public class ST {
    public static void main(String[] args) throws IOException {
        int count = nextInt();
        ArrayList<Integer> points = new ArrayList<>();
        int[] pointsX = new int[count];
        Integer[] pointsY = new Integer[count];
        int c, v;
        for (int i = 0; i < count; i++) {
            c = nextInt();
            pointsX[i] = c;
            c = nextInt();
            pointsY[i] = c;
        }
        for (int i = 0; i < count; i++) {
            v = pointsY[i];
            if (pointsX[i] == 1) {
                points.add(v);
                Collections.sort(points, (x, y) -> y - x);
                System.out.print(Collections.binarySearch(points, v, (x, y) -> y - x));
                System.out.print(' ');
            } else {
                points.remove(v);
            }
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
