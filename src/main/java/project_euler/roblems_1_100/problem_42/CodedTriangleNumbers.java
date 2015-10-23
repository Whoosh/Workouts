package project_euler.roblems_1_100.problem_42;

import project_euler.sub_code.CustomMathFunctions;
import project_euler.sub_code.FileFunctions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=42
 */
public class CodedTriangleNumbers {

    public static final int START = 1;
    public static final int T_VALUES_COUNT = 50;
    private static ArrayList<Integer> words = new ArrayList<>();

    public static void main(String[] args) {
        prepareWords();
        Set<Integer> triangleNumbers = generateTriangleNumbers();
        System.out.println(words.stream().filter(triangleNumbers::contains).count());
    }

    private static Set<Integer> generateTriangleNumbers() {
        return IntStream
                .range(START, T_VALUES_COUNT)
                .map(n -> (int) (0.5 * n * (n + 1)))
                .boxed()
                .collect(Collectors.toSet());
    }

    private static void prepareWords() {
        for (String fileLine : FileFunctions.getFileLines("src/project_euler/problems_1_100.problem_42/words.txt")) {
            words.addAll(Arrays
                    .asList(fileLine.replace("\"", "").split("[,]"))
                    .stream()
                    .map(CustomMathFunctions::alphanumericSumOf)
                    .mapToInt(Long::intValue)
                    .boxed()
                    .collect(Collectors.toList()));
        }
    }
}
