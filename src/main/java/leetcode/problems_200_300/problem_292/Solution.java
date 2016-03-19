package leetcode.problems_200_300.problem_292;

/**
 * Created by whoosh on 3/19/16.
 * https://leetcode.com/problems/nim-game/
 */
public class Solution {

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(i + "  -  " + canWinNim(i));
        }
    }

    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
