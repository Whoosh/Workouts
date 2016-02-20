package leetcode.problems_0_100.problem_14;

/**
 * Created by whoosh on 2/8/16.
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"a", "b"}));
        System.out.println(longestCommonPrefix(new String[]{"aa", "ab"}));
        System.out.println(longestCommonPrefix(new String[]{"aca", "cba11111111"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        int prefixLength = maxLen(strs[0], strs[1], strs[0].length() > strs[1].length() ? strs[1].length() : strs[0].length());
        for (int i = 1; i < strs.length - 1; i++) {
            if (strs[i + 1].length() == 0 || strs[i].length() == 0) return "";
            prefixLength = maxLen(strs[i], strs[i + 1], prefixLength);
        }
        return strs[0].substring(0, prefixLength);
    }

    private static int maxLen(String a, String b, int top) {
        final char[] chars_a = a.toCharArray();
        final char[] chars_b = b.toCharArray();
        if (top > chars_b.length) top = chars_b.length;
        for (int i = 0; i < top; i++) {
            if (chars_a[i] != chars_b[i]) return i;
        }
        return top;
    }
}
