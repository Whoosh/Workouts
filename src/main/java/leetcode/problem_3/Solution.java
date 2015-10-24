package leetcode.problem_3;

/**
 * Created by whoosh on 10/25/15.
 */
public class Solution {
    public static void main(String[] args) {
        int result = new Solution().lengthOfLongestSubstring("aabc");
        System.out.println(result);
    }

    public int lengthOfLongestSubstring(String s) {
        boolean[] map = new boolean[127];
        boolean[] map2 = new boolean[127];
        int max = 0;
        for (int i = 0, count = 0; i < s.length(); i++, count = 0) {
            System.arraycopy(map2, 0, map, 0, 127); // Seems faster than Array.fill(); but added little bit memory for addition map
            for (int j = i; j < s.length(); j++, count++) {
                if (map[s.charAt(j)]) break;
                map[s.charAt(j)] = true;
            }
            if (count > max) max = count;
        }
        return max;
    }
}
