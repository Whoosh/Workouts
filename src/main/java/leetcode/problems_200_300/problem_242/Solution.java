package leetcode.problems_200_300.problem_242;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by whoosh on 6/8/16.
 */
public class Solution {
    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        final HashMap<Character, Integer> map = new HashMap<>(t.length());
        final char[] first = s.toCharArray();
        final char[] second = t.toCharArray();
        Integer val;
        for (char c : first) {
            val = map.get(c);
            if (val == null) map.put(c, 1);
            else map.replace(c, val, val + 1);
        }
        for (char c : second) {
            val = map.get(c);
            if (val == null) return false;
            if (val < 1) return false;
            map.replace(c, val, val - 1);
        }
        return true;
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        final char[] first = s.toCharArray();
        final char[] second = t.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.hashCode(first) == Arrays.hashCode(second);
    }
}
