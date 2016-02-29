package leetcode.problems_300_400.problem_335;

/**
 * Created by whoosh on 2/27/16.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(isSelfCrossing(new int[]{2, 2, 2, 2}));
    }

    public static boolean isSelfCrossing(int[] x) {
        if (x == null || x.length < 4) return false;
        for (int i = 0; i < x.length - 3; i++) {
            if (x[i] == x[i + 1] && x[i + 2] == x[i] && x[i + 3] == x[i]) return true;
            if (x[i] >= x[i + 2] && x[i + 1] <= x[i + 3]) return true;
        }
        return false;
    }
}
