package stepic.algs_base_1.module_4;

import java.io.IOException;

/**
 * Created by whoosh on 11/30/15.
 */

public class DynamicLISHard {


    public static void main(String[] args) throws IOException {
        int count = nextInt();
        int[] oneStepBefore = new int[count];
        int[] indexes = new int[count + 1];
        int[] map = new int[count + 1];
        Point[] points = new Point[count];
        int length = 0;
        for (int i = 0; i < count; i++) {
            map[i] = Integer.MAX_VALUE;
            points[i] = new Point(nextInt(), i + 1);
        }
        indexes[0] = -1;
        map[0] = Integer.MIN_VALUE;
        map[count] = Integer.MAX_VALUE;
        for (int i = 0; i < points.length / 2; i++) {
            Point b = points[i];
            points[i] = points[points.length - i - 1];
            points[points.length - i - 1] = b;
        }
        int j = 0;
        for (int i = 0; i < count; i++) {
            if (i != 0 && points[i] == points[i - 1]) {
                j++;
                map[j] = points[i].val;
                indexes[j] = i;
                oneStepBefore[i] = indexes[j - 1];
                length = Math.max(length, j);
                continue;
            }

            j = bSearchRight(map, points[i].val, 0);

            if (map[j - 1] <= points[i].val && points[i].val <= map[j]) {
                map[j] = points[i].val;
                indexes[j] = i;
                oneStepBefore[i] = indexes[j - 1];
                length = Math.max(length, j);
            }
        }
        System.out.println(length);
        for (int index = indexes[length]; index != -1; index = oneStepBefore[index]) {
            System.out.print(points[index].index + " ");
        }
    }

    private static int bSearchRight(int[] map, int e, int start) {
        int low = start;
        int high = map.length - 1;
        int m;
        while (low <= high) {
            m = low + (high - low) / 2;
            if (map[m] <= e) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
        return high + 1;
    }

    private static int nextInt() throws IOException {
        int d;
        int val = 0;
        while ((d = System.in.read()) == ' ') ;
        do {
            val += d - 48;
            if ((d = System.in.read()) < 48 || d > 57) break;
            val *= 10;
        } while (true);
        return val;
    }

    private static class Point implements Comparable<Point> {
        int val;
        int index;

        public Point(int val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Point o) {
            return val - o.val;
        }
    }
}
