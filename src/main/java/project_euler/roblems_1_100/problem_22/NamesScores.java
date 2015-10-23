package project_euler.roblems_1_100.problem_22;

import project_euler.sub_code.FileFunctions;
import project_euler.sub_code.CustomMathFunctions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Using names.txt a 46K text file containing over five-thousand first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
 * What is the total of all the name scores in the file?
 * https://projecteuler.net/problem=21
 */

public class NamesScores {

    private final static String PROBLEM = "src/project_euler/problems_1_100.problem_22/names.txt";
    private static int counter = 1;

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        for (String fileLine : FileFunctions.getFileLines(PROBLEM))
            names.addAll(Stream.of(fileLine.split("[,]")).map(s -> s.replace("\"", "")).collect(Collectors.toList()));
        Collections.sort(names);
        System.out.println(names.stream().mapToLong(s -> CustomMathFunctions.alphanumericSumOf(s) * counter++).sum());
    }
}
