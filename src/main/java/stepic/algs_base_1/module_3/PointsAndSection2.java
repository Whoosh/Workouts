package stepic.algs_base_1.module_3;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by whoosh on 11/22/15.
 */

public class PointsAndSection2 {

    private static int[][] sections;
    private static int[] points;
    private static HashMap<Integer, Integer> pointz;

    public static void main(String[] args) throws IOException {
        readValues();
        spitAndPrint();
    }

    private static void readValues() throws IOException {
        int sCount = nextInt();
        int pCount = nextInt();
        HashMap<Integer, int[]> test = new HashMap<>(sCount);
        pointz = new HashMap<>(pCount);
        int start, end;
        int[] value;
        int[] newArray;
        for (int i = 0; i < sCount; i++) {
            start = nextInt();
            end = nextInt();
            newArray = new int[]{start, end, 1};
            value = test.get(Arrays.hashCode(newArray));
            if (value != null) {
                value[2]++;
                test.replace(Arrays.hashCode(newArray), value);
            } else {
                test.put(Arrays.hashCode(newArray), newArray);
            }
        }
        sections = new int[test.size()][];
        points = new int[pCount];
        int k = 0;
        for (int[] values : test.values()) {
            sections[k] = values;
            k++;
        }
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
        int j = sections.length - 1, i;

        for (int point : points) {

            if (pointz.containsKey(point)) {
                System.out.println(pointz.get(point));
                System.out.print(' ');
                continue;
            }

            Arrays.sort(sections, 0, j + 1, (o1, o2) -> o1[0] - o2[0]);

            j = bSearchRight(sections, point, (o1, o2) -> {
                if (o2[0] > o1[0]) return 0;
                return o1[0] - o2[0];
            });

            if (j < 0) {
                System.out.print(0);
                System.out.print(' ');
                continue;
            }

            Arrays.sort(sections, 0, j + 1, (o1, o2) -> o1[1] - o2[1]);

            i = bSearchLeftT(sections, j + 1, point, (o1, o2) -> o2[1] < o1[1] ? 0 : o1[1] - o2[1]);

            if (i < 0) {
                System.out.print(0);
                System.out.print(' ');
                continue;
            }
            int sum = 0;
            for (int k = i; k < j + 1; k++) {
                sum += sections[k][2];
            }
            pointz.put(point, sum);
            System.out.print(sum);
            System.out.print(' ');
        }
    }

    private static int bSearchLeftT(int[][] map, int end, int e, Comparator<int[]> c) {
        int low = 0;
        int high = end - 1;
        int lowerFoundedElement = Integer.MIN_VALUE;
        int m;
        int[] a = new int[]{e, e};
        while (low <= high) {
            m = low + (high - low) / 2;
            if (c.compare(map[m], a) == 0) {
                lowerFoundedElement = m;
                high = m - 1;
            } else if (c.compare(map[m], a) < 1) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
        if (lowerFoundedElement == Integer.MIN_VALUE) return -1;
        return lowerFoundedElement;
    }

    private static int bSearchRight(int[][] map, int e, Comparator<int[]> c) {
        int low = 0;
        int high = map.length - 1;
        int lowerFoundedElement = Integer.MIN_VALUE;
        int m;
        while (low <= high) {
            m = low + (high - low) / 2;
            if (c.compare(map[m], new int[]{e, e}) == 0) {
                lowerFoundedElement = m;
                low = m + 1;
            } else if (c.compare(map[m], new int[]{e, e}) < 1) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
        if (lowerFoundedElement == Integer.MIN_VALUE) return -1;
        return lowerFoundedElement;
    }
}
