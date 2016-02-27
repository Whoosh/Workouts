package leetcode.problems_300_400.problem_334;

/**
 * Created by whoosh on 2/20/16.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{1, 4, 4, 4, 4, 3, 4, 5}));
    }

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        if (nums.length == 3) return nums[0] < nums[1] && nums[1] < nums[2];
        int min = Integer.MAX_VALUE;
        int m = min;
        for (int n : nums) {
            if (min >= n) min = n;
            else if (n < m) m = n;
            else if (n > m) return true;
        }
        return false;
    }
}
