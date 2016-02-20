package leetcode.problems_0_100.problem_16;

import java.util.Arrays;

/**
 * Created by whoosh on 2/11/16.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(threeSumClosest(new int[]{-123, 0, 1, 1, 1, 2, 22, 2, 2, 2, 2, 2, 2, -1, 1, 1, -2312, 123, -40, -122}, 50));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = Integer.MAX_VALUE;
        int d = Integer.MAX_VALUE;
        int t;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1, k = nums.length - 1; k > j; ) {
                final int bSum = nums[i] + nums[j] + nums[k];
                if (bSum == target) return target;
                if (bSum > target) k--;
                else j++;
                if ((t = Math.abs(bSum - target)) < d) {
                    d = t;
                    sum = bSum;
                }
            }
        }
        return sum;
    }
}
