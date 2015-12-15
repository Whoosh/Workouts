package stepic.algs_csc_base_1.module_3;

import java.util.*;

/**
 * Created by whoosh on 11/22/15.
 */

public class PointsAndSection {

    private static ArrayList<S> sections;
    private static ArrayList<S> points;
    private static ArrayList<ArrayList<S>> map;

    public static void main(String[] args) {
        readValues();
        sortSections();
        collectSections();
        spitAndPrint(map, points);
    }

    private static void collectSections() {
        for (int i = 0; i < sections.size(); i++) {
            final ArrayList<S> mapElement = new ArrayList<>();
            final S element = sections.get(i);
            while (i < sections.size() - 1 && element.equals(sections.get(i + 1))) {
                mapElement.add(sections.get(i++ + 1));
            }
            mapElement.add(element);
            map.add(mapElement);
        }
    }

    private static void sortSections() {
        Collections.sort(sections, (o1, o2) -> o1.a - o2.a);
        Collections.sort(sections, (o1, o2) -> o1.a == o2.a ? o1.b - o2.b : 0);
    }

    private static void readValues() {
        Scanner scanner = new Scanner(System.in);
        int sCount = scanner.nextInt();
        int pCount = scanner.nextInt();
        sections = new ArrayList<>(sCount);
        points = new ArrayList<>(pCount);
        map = new ArrayList<>(sCount);
        int start, end;
        S element;
        for (int i = 0; i < sCount; i++) {
            start = scanner.nextInt();
            end = scanner.nextInt();
            element = new S(start, end);
            sections.add(element);
        }
        int v;
        for (int i = 0; i < pCount; i++) {
            v = scanner.nextInt();
            points.add(new S(v, v));
        }
    }

    private static void spitAndPrint(ArrayList<ArrayList<S>> map, ArrayList<S> points) {
        ArrayList<S>[] arrayLists = map.toArray(new ArrayList[map.size()]);
        for (S point : points) {
            ArrayList<S> e = new ArrayList<>();
            e.add(point);
            int i = bSearchLeft(map, e, (o1, o2) -> {
                int a1 = o1.get(0).a;
                int a2 = o2.get(0).a;
                if (a2 > a1) return 0;
                return a1 - a2;
            });

            int j = bSearchRight(map, e, (o1, o2) -> {
                int a1 = o1.get(0).a;
                int a2 = o2.get(0).a;
                if (a2 > a1) return 0;
                return a1 - a2;
            });

            if (i < 0 || j < 0) {
                System.out.print(0);
                System.out.print(' ');
                continue;
            }
            ArrayList<ArrayList<S>> map2 = new ArrayList<>(j - i);

            for (; i <= j; i++) map2.add(map.get(i));

            Collections.sort(map2, (o1, o2) -> o1.get(0).b - o2.get(0).b);

            i = bSearchLeft(map2, e, (o1, o2) -> {
                int b1 = o1.get(0).b;
                int b2 = o2.get(0).b;
                if (b2 < b1) return 0;
                return b1 - b2;
            });
            if (i < 0) {
                System.out.print(0);
                System.out.print(' ');
                continue;
            }
            printResultForPoint(map2, i);
        }
    }

    private static void printResultForPoint(ArrayList<ArrayList<S>> map2, int start) {
        int count = 0;
        for (int i = start; i < map2.size(); i++) {
            count += map2.get(i).size();
        }
        System.out.print(count);
        System.out.print(' ');
    }

    private static int bSearchLeft(ArrayList<ArrayList<S>> map, ArrayList<S> e, Comparator<ArrayList<S>> c) {
        int low = 0;
        int high = map.size() - 1;
        int lowerFoundedElement = Integer.MIN_VALUE;
        int m;
        while (low <= high) {
            m = low + (high - low) / 2;
            if (c.compare(map.get(m), e) == 0) {
                lowerFoundedElement = m;
                high = m - 1;
            } else if (c.compare(map.get(m), e) < 1) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
        if (lowerFoundedElement == Integer.MIN_VALUE) return -1;
        return lowerFoundedElement;
    }

    private static int bSearchRight(ArrayList<ArrayList<S>> map, ArrayList<S> e, Comparator<ArrayList<S>> c) {
        int low = 0;
        int high = map.size() - 1;
        int lowerFoundedElement = Integer.MIN_VALUE;
        int m;
        while (low <= high) {
            m = low + (high - low) / 2;
            if (c.compare(map.get(m), e) == 0) {
                lowerFoundedElement = m;
                low = m + 1;
            } else if (c.compare(map.get(m), e) < 1) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
        if (lowerFoundedElement == Integer.MIN_VALUE) return -1;
        return lowerFoundedElement;
    }


    private static class S {
        int a, b;

        S(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            S s = (S) o;
            return a == s.a && b == s.b;
        }

        @Override
        public String toString() {
            return "S{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    public static void nSquare() {
        Scanner scanner = new Scanner(System.in);
        int sCount = scanner.nextInt();
        int pCount = scanner.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>(Integer.MAX_VALUE / 1000);
        for (int i = 0; i < sCount; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            for (int j = start; j < end; j++) {
                if (map.containsKey(j)) {
                    map.put(j, map.get(j) + 1);
                } else {
                    map.put(j, 1);
                }
            }
        }
        Integer s;
        for (int i = 0; i < pCount; i++) {
            s = map.get(scanner.nextInt());
            if (s == null) s = 0;
            System.out.print(s + " ");
        }
    }
}
