package random_tasks;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Created by whoosh on 3/29/16.
 */
public class TopologySort {


    public static int[] sortArrayByRecursion(int[][] graph) {
        ArrayList<Integer> result = new ArrayList<>();
        map = new boolean[graph.length * 2];
        fillTheResultRecursively(graph, result, 0);
        return result.stream().mapToInt(x -> x).toArray();
    }

    public static int[] sortArrayByCycle(int[][] graph) {
        ArrayList<Integer> result = new ArrayList<>();
        map = new boolean[graph.length * 2];
        fillTheByCycle(graph, result);
        return result.stream().mapToInt(x -> x).toArray();
    }

    private static boolean[] map;

    private static void fillTheResultRecursively(int[][] graph, ArrayList<Integer> result, int nodeIndex) {
        if (map[nodeIndex]) return;
        map[nodeIndex] = !map[nodeIndex];
        IntStream.of(graph[nodeIndex]).peek(x -> fillTheResultRecursively(graph, result, x)).count();
        result.add(nodeIndex);
    }

    private static void fillTheByCycle(int[][] graph, ArrayList<Integer> result) {
        // TODO
    }
}
