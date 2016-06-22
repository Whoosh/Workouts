package project_euler.roblems_1_100.problem_81;

import project_euler.sub_code.FileFunctions;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by whoosh on 6/21/16.
 */
public class PathSumTwoWays {
    private static final String FILE = "/home/whoosh/IdeaProjects/Workouts/src/main/java/project_euler/roblems_1_100/problem_81/matrix";
    private static final int H = 80;
    private static final int W = 80;

    private static int[][] graph = new int[H][W];
    private static int[][] weight = new int[H][W];

    public static void main(String[] args) {
        fillMap();
        findPath();
        System.out.println(findPath());
    }

    private static int findPath() {
        weight[0][0] = graph[0][0];
        for (int i = 0; i < H - 1; i++) {
            for (int j = 0; j < W - 1; j++) {
                calcSumForRightStep(i, j);
                calcSumForDownStep(i, j);
            }
        }
        checkRightSide();
        checkBottomSide();
        return weight[H - 1][W - 1];
    }

    private static void checkRightSide() {
        final int prev = H - 1;
        for (int j = 0; j < prev; j++) {
            calcSumForRightStep(prev, j);
        }
    }

    private static void checkBottomSide() {
        final int prev = W - 1;
        for (int i = 0; i < prev; i++) {
            calcSumForDownStep(i, prev);
        }
    }

    private static void calcSumForRightStep(int i, int j) {
        if (weight[i][j] + graph[i][j + 1] < weight[i][j + 1]) {
            weight[i][j + 1] = weight[i][j] + graph[i][j + 1];
        }
    }

    private static void calcSumForDownStep(int i, int j) {
        if (weight[i][j] + graph[i + 1][j] < weight[i + 1][j]) {
            weight[i + 1][j] = weight[i][j] + graph[i + 1][j];
        }
    }

    private static void fillMap() {
        List<String> fileLines = FileFunctions.getFileLines(FILE);
        int index = 0;
        for (String fileLine : fileLines) {
            weight[index] = IntStream.generate(() -> Integer.MAX_VALUE).limit(W).toArray();
            graph[index++] = Stream.of(fileLine.split("[,]")).mapToInt(Integer::valueOf).toArray();
        }
    }
}
