package leetcode.problems_0_100.problem_17;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whoosh on 2/11/16.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(letterCombinations("234"));
    }

    private static char[][] map = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'},
            {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r',
            's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};

    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) return result;
        char[] chars = digits.toCharArray();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) ints[i] = chars[i] - 48;
        foundAll(ints, 0, 0, result, new char[ints.length]);

        return result;
    }

    private static void foundAll(int[] numbers, int f, int index, List<String> result, char[] bResult) {
        if (index == numbers.length) {
            result.add(new String(bResult));
            return;
        }
        for (int i = 0; i < map[numbers[index]].length; i++) {
            bResult[f] = map[numbers[index]][i];
            foundAll(numbers, f + 1, index + 1, result, bResult);
        }
    }
}
