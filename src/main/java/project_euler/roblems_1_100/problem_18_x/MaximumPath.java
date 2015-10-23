package project_euler.roblems_1_100.problem_18_x;


import project_euler.sub_code.FileFunctions;
import project_euler.sub_code.StartWithLazzyBenchmark;
import project_euler.sub_code.Starter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://projecteuler.net/problem=18
 */

public class MaximumPath implements Starter {

    private static final String fileLocation = "/home/whoosh/IdeaProjects/ProjectEuler/src/main/java/problems_1_100/problem_18_x/location_67.txt";

    private static final int RIGHT = 1;
    private static final int LEFT = 0;
    private static final int ARRAY_CELL_OFFSET = 1;

    private int[] location;
    private int max_lvl;
    private HashMap<Integer, Wall> walls = new HashMap<>();
    private HashMap<Integer, Integer> solvedPaths = new HashMap<>();

    public static void main(String[] args) {
        new MaximumPath().start();
    }

    private void readLocationFromFile() {
        List<int[]> collect = FileFunctions.getFileLines(fileLocation).stream().map(this::splitString).collect(Collectors.toList());
        location = new int[collect.stream().map(x -> x.length).reduce((x, y) -> x + y).get()];
        int k = 0; // faster then reduce and realloc
        for (int[] cells : collect)
            for (int cell : cells) location[k++] = cell;
    }

    private int[] splitString(String s) {
        String[] split = s.split("[ ]");
        int[] result = new int[split.length];
        for (int i = 0; i < result.length; i++)
            result[i] = Integer.parseInt(split[i]);
        return result;
    }

    private void calculateMaxLvlAndWalls() {
        for (int i = 1; i < location.length + 1; i = i + max_lvl + 1)
            walls.put(max_lvl, new Wall(i - max_lvl++, i));
        max_lvl--;
    }

    @StartWithLazzyBenchmark
    public void run() {
        readLocationFromFile();
        calculateMaxLvlAndWalls();
        System.out.println(findPathRecFromBottomToTopExactly(1, 0));
        System.out.println(findPathByCycleFromTopToBottomApproximately());
    }

    private int findPathRecFromBottomToTopExactly(int cell, int lvl) {
        if (lvl == max_lvl) {
            return getLocationValue(cell);
        } else {
            final int leftCell = leftDiagonalCell(cell, lvl);
            final int rightCell = rightDiagonalCell(cell, lvl);
            final int maxLeft = bufferOrRec(leftCell, lvl, cell);
            final int maxRight = bufferOrRec(rightCell, lvl, cell);
            return maxLeft < maxRight ? maxRight : maxLeft;
        }
    }

    private int bufferOrRec(int cellTo, int lvl, int cellFrom) {
        if (solvedPaths.containsKey(cellTo)) {
            return solvedPaths.get(cellTo);
        } else {
            final int cellSum = findPathRecFromBottomToTopExactly(cellTo, lvl + 1) + getLocationValue(cellFrom);
            solvedPaths.put(cellTo, cellSum);
            return cellSum;
        }
    }

    private int findPathByCycleFromTopToBottomApproximately() {
        int cell = 1, rightCell, leftCell;
        int result = getLocationValue(cell);
        for (int lvl = 0; lvl < max_lvl; lvl++) {
            rightCell = rightDiagonalCell(cell, lvl);
            leftCell = leftDiagonalCell(cell, lvl);
            cell = findMaxPath(leftCell, lvl + 1) < findMaxPath(rightCell, lvl + 1) ? rightCell : leftCell;
            result += getLocationValue(cell);
        }
        return result;
    }

    private int findMaxPath(int cell, int lvl) {
        int result = getLocationValue(cell);
        for (int i = lvl; i < max_lvl; i++) {
            cell = leftDiagonalCellWeight(cell, i) <= rightDiagonalCellWeight(cell, i) ? rightDiagonalCell(cell, i) : leftDiagonalCell(cell, i);
            result += getLocationValue(cell);
        }
        return result;
    }

    private int leftDiagonalCellWeight(int cell, int lvl) {
        final int nextCell = leftDiagonalCell(cell, lvl);
        return leftDiagonalStepAvailable(nextCell, lvl) ? getLocationValue(nextCell) : 0;
    }

    private int rightDiagonalCellWeight(int cell, int lvl) {
        final int nextCell = rightDiagonalCell(cell, lvl);
        return rightDiagonalStepAvailable(nextCell, lvl) ? getLocationValue(nextCell) : 0;
    }

    private int diagonalCell(int side, int cell, int lvl) {
        return walls.get(lvl + 1).getLeft() + cell - walls.get(lvl).getLeft() + side;
    }

    private int rightDiagonalCell(int cell, int lvl) {
        return diagonalCell(RIGHT, cell, lvl);
    }

    private int leftDiagonalCell(int cell, int lvl) {
        return diagonalCell(LEFT, cell, lvl);
    }

    private int getLocationValue(int cell) {
        return location[cell - ARRAY_CELL_OFFSET];
    }

    private boolean leftDiagonalStepAvailable(int nextCell, int lvl) {
        return walls.get(lvl + 1).getLeft() <= nextCell;
    }

    private boolean rightDiagonalStepAvailable(int nextCell, int lvl) {
        return walls.get(lvl + 1).getRight() >= nextCell;
    }
}
