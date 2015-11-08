package leetcode.problem_9;

/**
 * Created by whoosh on 11/8/15.
 */

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1111001111) + " 1111001111");
        System.out.println(isPalindrome(-2147447412) + " -2147447412");
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int mirror = 0, dx = x, t;
        while (x >= 10) {
            t = x / 10;
            mirror += x - t * 10;
            mirror *= 10;
            x = t;
        }
        return dx - (mirror+x) == 0;
    }
}
