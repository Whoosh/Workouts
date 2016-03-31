package leetcode.problems_100_200.problem_191;

/**
 * Created by whoosh on 3/31/16.
 */

public class Solution {

    public static void main(String[] args) {
        for (int i = -10; i < 10; i++) {
            System.out.println(hammingWeight(i));
        }
    }

    public static int hammingWeight(int n) {
        for (int d = n < 0 ? Integer.MAX_VALUE + n + (n = 1) : n + (n = 0); d > 0; n += (d & 1), d >>= 1) ;
        return n;
    }
}
