package project_euler.roblems_1_100.problem_61;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * https://projecteuler.net/problem=61
 */
public class CyclicalFigurateNumbers {

    // mathematical limits between min and max for 4 digits numbers;
    private static final int TR_MIN = 45, TR_MAX = 140;
    private static final int SQ_MIN = 32, SQ_MAX = 99;
    private static final int PEN_MIN = 26, PEN_MAX = 81;
    private static final int HEX_MIN = 23, HEX_MAX = 70;
    private static final int HEP_MIN = 21, HEP_MAX = 63;
    private static final int OC_MIN = 19, OC_MAX = 58;

    private static HashMap<Integer, TreeSet<Integer>> triangles = initMap(TR_MIN, TR_MAX, CyclicalFigurateNumbers::triangle);
    private static HashMap<Integer, TreeSet<Integer>> squares = initMap(SQ_MIN, SQ_MAX, CyclicalFigurateNumbers::square);
    private static HashMap<Integer, TreeSet<Integer>> pentagonals = initMap(PEN_MIN, PEN_MAX, CyclicalFigurateNumbers::pentagonal);
    private static HashMap<Integer, TreeSet<Integer>> hexagonal = initMap(HEX_MIN, HEX_MAX, CyclicalFigurateNumbers::hexagonal);
    private static HashMap<Integer, TreeSet<Integer>> heptagonal = initMap(HEP_MIN, HEP_MAX, CyclicalFigurateNumbers::heptagonal);
    private static HashMap<Integer, TreeSet<Integer>> octagonal = initMap(OC_MIN, OC_MAX, CyclicalFigurateNumbers::octagonal);

    //{squares, heptagonal, octagonal, hexagonal, pentagonals} // брутфорс, для порядка.
    public static void main(String[] args) {
        int sum = 0;
        HashMap[] head = new HashMap[]{squares, heptagonal, octagonal, hexagonal, pentagonals};
        ArrayList<ArrayList<Integer>> result = findCycle(head, triangles);
        for (ArrayList<Integer> path : result) {
            System.out.println(path);
            for (Integer integer : path) sum += integer;
            System.out.println(sum);
        }
    }

    private static ArrayList<ArrayList<Integer>> findCycle(HashMap<Integer, TreeSet<Integer>>[] head, HashMap<Integer,
            TreeSet<Integer>> start) {
        ArrayList<Integer> path;
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<Integer>> entry : start.entrySet()) {
            for (Integer first : entry.getValue()) {
                path = findPath(first, head);
                if (path.size() == 6 && isPathCycling(path)) {
                    Collections.sort(path);
                    paths.add(path);
                }
            }
        }
        return paths;
    }

    private static ArrayList<Integer> findPath(int entryPoint, HashMap<Integer, TreeSet<Integer>>[] maps) {
        final ArrayList<Integer> path = new ArrayList<>();
        path.add(entryPoint);
        for (HashMap<Integer, TreeSet<Integer>> map : maps) {
            TreeSet<Integer> nodes = map.get(getNumberTail(entryPoint));
            if (nodes != null) {
                if (nodes.size() > 1) entryPoint = nodes.pollFirst();
                else entryPoint = nodes.first();
                path.add(entryPoint);
            } else break;
        }
        return path;
    }

    private static boolean isPathCycling(ArrayList<Integer> path) {
        boolean t = false;
        for (int i = 0; i < path.size(); i++, t = false) {
            for (Integer aPath : path)
                if (getNumberTail(path.get(i)) == getNumberHead(aPath)) t = true;
            if (!t) return false;
        }
        return true;
    }

    private static HashMap<Integer, TreeSet<Integer>> initMap(int min, int max, UnaryOperator<Integer> op) {
        final HashMap<Integer, TreeSet<Integer>> result = new HashMap<>();
        int buffer, key;
        TreeSet<Integer> treeBuffer;
        for (int i = min; i <= max; ) {
            treeBuffer = new TreeSet<>();
            buffer = op.apply(i);
            if (getNumberTail(buffer) < 10) {
                i++;
                continue;
            }
            key = getNumberHead(buffer);
            while (getNumberHead((buffer = op.apply(i))) == key) {
                treeBuffer.add(buffer);
                i++;
            }
            result.put(key, treeBuffer);
        }
        return result;
    }

    private static int getNumberHead(int seed) {
        return seed / 100;
    }

    private static int getNumberTail(int seed) {
        return seed % 100;
    }

    private static int square(int n) {
        return n * n;
    }

    private static int triangle(int n) {
        return n * (n + 1) / 2;
    }

    private static int pentagonal(int n) {
        return n * (3 * n - 1) / 2;
    }

    private static int hexagonal(int n) {
        return n * (2 * n - 1);
    }

    private static int heptagonal(int n) {
        return n * (5 * n - 3) / 2;
    }

    private static int octagonal(int n) {
        return n * (3 * n - 2);
    }
}
