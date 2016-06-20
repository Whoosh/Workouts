package random_tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by whoosh on 3/29/16.
 */
public class TopologySort {

    private static States[] mapOfNodeState;
    private static List<Integer> arrayResultBuffer;
    private static boolean graphGotCycle = false;

    private enum States {WHITE, GREEN, RED}

    public static int[] sortArrayByRecursion(int[][] graph) {
        clearBuffer(graph);
        fillTheResultRecursively(graph, 0);
        if (graphGotCycle) return new int[0];
        return arrayResultBuffer.stream().mapToInt(x -> x).toArray();
    }

    private static void fillTheResultRecursively(int[][] graph, int nodeIndex) {
        if (mapOfNodeState[nodeIndex] == States.GREEN || graphGotCycle) return;
        if (mapOfNodeState[nodeIndex] == States.RED) {
            graphGotCycle = true;
            return;
        }
        mapOfNodeState[nodeIndex] = States.RED;
        IntStream.of(graph[nodeIndex]).forEachOrdered(x -> fillTheResultRecursively(graph, x));
        arrayResultBuffer.add(nodeIndex);
        mapOfNodeState[nodeIndex] = States.GREEN;
    }

    private static void clearBuffer(int[][] graph) {
        mapOfNodeState = new States[graph.length * 2];
        arrayResultBuffer = new ArrayList<>();
        Arrays.setAll(mapOfNodeState, x -> States.WHITE);
        graphGotCycle = false;
    }
}
