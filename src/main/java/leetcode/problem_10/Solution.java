package leetcode.problem_10;

/**
 * Created by whoosh on 11/8/15.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aaa","aa"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aa", ".*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
    }

    public static boolean isMatch(String s, String p) {
        return s.matches(p);
    }
}
