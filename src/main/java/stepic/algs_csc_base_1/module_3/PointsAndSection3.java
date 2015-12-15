package stepic.algs_csc_base_1.module_3;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by whoosh on 11/22/15.
 */

public class PointsAndSection3 {

    private static int[] sections_a;
    private static int[] sections_b;
    private static int[] points;
    private static HashMap<Integer, Integer> pointz;

    public static void main(String[] args) throws IOException {
        readValues();
        spitAndPrint();
    }

    private static void readValues() throws IOException {
        int sCount = nextInt();
        int pCount = nextInt();
        pointz = new HashMap<>(pCount);
        int start, end;
        sections_a = new int[sCount];
        sections_b = new int[sCount];
        for (int i = 0; i < sCount; i++) {
            start = nextInt();
            end = nextInt();
            sections_a[i] = start;
            sections_b[i] = end;
        }
        points = new int[pCount];
        for (int i = 0; i < pCount; i++) {
            points[i] = nextInt();
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

    private static void spitAndPrint() {
        int result = 0;
        Arrays.sort(sections_a);
        Arrays.sort(sections_b);
        for (int point : points) {
            if (pointz.containsKey(point)) {
                System.out.println(pointz.get(point));
                System.out.print(' ');
                continue;
            }
            result = bSearchLeft(sections_a, point) - bSearchRight(sections_b, point);
            pointz.put(point, result);
            System.out.print(result);
            System.out.print(' ');
        }
    }

    private static int bSearchLeft(int[] map, int e) {
        int low = 0;
        int high = map.length - 1;
        int m;
        while (low <= high) {
            m = low + (high - low) / 2;
            if (map[m] > e) {
                high = m - 1;
            } else {
                low = m + 1;
            }
        }
        return high + 1;
    }

    private static int bSearchRight(int[] map, int e) {
        int low = 0;
        int high = map.length - 1;
        int m;
        while (low <= high) {
            m = low + (high - low) / 2;
            if (map[m] < e) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
        return high + 1;
    }
}
