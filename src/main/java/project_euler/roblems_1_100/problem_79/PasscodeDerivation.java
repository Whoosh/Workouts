package project_euler.roblems_1_100.problem_79;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static project_euler.sub_code.CustomMathFunctions.numberToArray;
import static project_euler.sub_code.FileFunctions.getFileLines;

public class PasscodeDerivation {
    private static final String FILE = "/home/whoosh/IdeaProjects/Workouts/src/main/java/project_euler/roblems_1_100/problem_79/passcode";

    public static void main(String[] args) {
        ArrayList<Integer[]> asArrays = getFileLines(FILE)
                .stream()
                .distinct()
                .map(x -> numberToArray(Integer.parseInt(x)))
                .collect(Collectors.toCollection(ArrayList::new));
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        for (int i = 0; i < 10; i++) graph.put(i, new HashSet<>());
        for (Integer[] nodes : asArrays) {
            HashSet<Integer> line = graph.get(nodes[0]);
            line.add(nodes[1]);
            line.add(nodes[2]);
            HashSet<Integer> next = graph.get(nodes[1]);
            next.add(nodes[2]);
        }
        int max = 0, index = 0, l;
        for (int i = 0; i < 10; i++) {
            l = sortArrayByRecursion(graph, i).length;
            if (l > max) {
                index = i;
                max = l;
            }
        }
        System.out.println(IntStream.of(sortArrayByRecursion(graph, index)).boxed().map(x -> x + "").reduce((x, y) -> y + "" + x).get());
    }

    private static States[] mapOfNodeState;
    private static List<Integer> result;
    private static boolean graphGotCycle = false;

    private enum States {WHITE, GREEN, RED}

    public static int[] sortArrayByRecursion(HashMap<Integer, HashSet<Integer>> graph, int start) {
        clearBuffer();
        fillTheResultRecursively(graph, start);
        if (graphGotCycle) return new int[0];
        return result.stream().mapToInt(x -> x).toArray();
    }

    private static void clearBuffer() {
        mapOfNodeState = new States[10];
        result = new ArrayList<>();
        Arrays.setAll(mapOfNodeState, x -> States.WHITE);
        graphGotCycle = false;
    }

    private static void fillTheResultRecursively(HashMap<Integer, HashSet<Integer>> graph, int node) {
        if (mapOfNodeState[node] == States.GREEN || graphGotCycle) return;
        if (mapOfNodeState[node] == States.RED) {
            graphGotCycle = true;
            return;
        }
        mapOfNodeState[node] = States.RED;
        graph.get(node).stream().forEachOrdered(x -> fillTheResultRecursively(graph, x));
        result.add(node);
        mapOfNodeState[node] = States.GREEN;
    }

}
