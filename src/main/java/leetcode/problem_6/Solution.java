package leetcode.problem_6;

/**
 * Created by root on 10/31/15.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 5));
    }
    
    public static String convert(String src, int numRows) {
        int len = src.length();
        if (numRows < 2 || len <= numRows) return src;

        char[] source = src.toCharArray();
        char[] result = new char[len];

        int result_i;
        int source_i;

        int topStep = (numRows - 1) * 2;
        int minStep = 0;

        result_i = fillFloat(result, 0, source, 0, len, topStep);

        for (int i = 3; i <= numRows; i++) {
            source_i = 2 * numRows - i;
            result[result_i++] = source[i - 2];
            minStep += 2;
            for (int j = 2; source_i < len; j++) {
                result[result_i++] = source[source_i];
                source_i += (j & 1) == 0 ? minStep : topStep - minStep;
            }
        }
        fillFloat(result, result_i, source, numRows - 1, len, (numRows - 1) * 2);
        return new String(result);
    }

    private static int fillFloat(char[] result, int result_i, char[] source, int source_i, int len, int topStep) {
        result[result_i++] = source[source_i];
        while ((source_i += topStep) < len) result[result_i++] = source[source_i];
        return result_i;
    }
}
