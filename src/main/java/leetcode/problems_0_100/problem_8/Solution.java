package leetcode.problems_0_100.problem_8;

/**
 * Created by Whoosh on 11/4/15.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(myAtoi("123-+ 1111 21 1111111111"));
    }

    public static int myAtoi(String s) {
        if (s.length() == 0) return 0;
        long res = myAtoi2(s);
        if (res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) res;
    }

    public static long myAtoi2(String str) {
        char[] chars = str.toCharArray();
        int i = 0, j;
        boolean s = false;
        while (!isDigit(chars[i]) && i < chars.length - 1) i++;
        if (!isDigit(chars[i])) return 0;
        if (i == 0) s = false;
        if (i > 0) {
            if (chars[i - 1] != ' ' && chars[i - 1] != '+' && chars[i - 1] != '-') return 0;
            s = chars[i - 1] == '-';
        }
        for (int k = 0; k < i - 1; k++) if (!isDigit(chars[k]) && chars[k] != ' ') return 0;
        j = i;
        while (isDigit(chars[j]) && j < chars.length - 1) j++;
        if (j == chars.length - 1 && isDigit(chars[j])) j++;
        if (j - i > 10) j = i + 11;
        return Long.valueOf(str.substring(i, j)) * (s ? -1 : 1);
    }

    public static boolean isDigit(char c) {
        return c < ':' && c > '/';
    }
}
