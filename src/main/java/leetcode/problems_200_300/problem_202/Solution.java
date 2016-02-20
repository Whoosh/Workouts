package leetcode.problems_200_300.problem_202;


/**
 * Created by whoosh on 2/20/16.
 */

public class Solution {
    public static void main(String[] args) {
        for (int i = 1; i < 400; i++) {
            if (isHappy(i))
                System.out.println(isHappy(i) + " " + i);
        }
    }

    public static boolean isHappy(int n) {
        int sum = 2, old;
        final boolean[] map = new boolean[730];
        while (sum > 1) {
            if (map[sum]) return false;
            map[sum] = true;
            sum = 0;
            while (n > 0) {
                old = n;
                n /= 10;
                old -= n * 10;
                sum += old * old;
            }
            n = sum;
        }
        return sum == 1;
    }
}
