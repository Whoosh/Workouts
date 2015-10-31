package leetcode.problem_6;

/**
 * Created by root on 10/31/15.
 */
public class Solution {

    public static void main(String[] args) {
        int len = 2;
        int[] map = new int[len];
        for (int i = 0; i < len; i++) {
            map[i] = i + 1;
        }
        System.out.println(convert("PAYPALISHIRING", 12));
    }

    public static String convert(String src, int numRows) {
        if (numRows < 2) return src;
        if (src.length() == 1) return src;
        if (src.length() <= numRows) return src;
        char[] s = src.toCharArray();
        StringBuilder result = new StringBuilder();
        int len = s.length;
        int index = 0;
        int topStep = (numRows - 1) * 2;
        int minStep = 0;

        result.append(s[index]);
        while ((index += topStep) < len) {
            result.append(s[index]);
        }
        for (int i = 2; i < numRows; i++) {
            index = 2 * numRows - i;
            topStep -= 2;
            minStep += 2;
            result.append(s[i - 1]);
            for (int j = 2; index <= len; j++) {
                result.append(s[index - 1]);
                if ((j & 1) == 0) index += minStep;
                else index += topStep;
            }
        }
        index = numRows;
        result.append(s[index - 1]);
        topStep = (numRows - 1) * 2;
        while ((index += topStep) <= len) {
            result.append(s[index - 1]);
        }

        return result.toString();
    }

    private static int second(int row, int prev) {
        return 0;
    }

    private static int first(int row, int prev) {
        return 0;
    }

}
