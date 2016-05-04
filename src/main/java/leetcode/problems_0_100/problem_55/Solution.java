package leetcode.problems_0_100.problem_55;

/**
 * Created by whoosh on 4/7/16.
 */
public class Solution {
    // TODO
    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump(new int[]{3, 222, 2, 1, 2, 1, 0, 4}));
        System.out.println(canJump(new int[]{1, 1, 1, 1}));
        System.out.println(canJump(new int[]{0, 1}));
        System.out.println(canJump(new int[]{1, 1}));
        System.out.println(canJump(new int[]{1, 1, 1}));
        System.out.println(canJump(new int[]{4, 0, 1, 3, 3, 3, 4, 4, 0, 4}));
        System.out.println(canJump(new int[]{1, 2, 0, 1}));
        System.out.println(canJump(new int[]{2, 0, 2, 0, 1}));
        System.out.println(canJump(new int[]{1, 1, 1, 0}));
        System.out.println(canJump(new int[]{5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0}));


    }

    public static boolean canJump(int[] nums) {
        if (nums[0] >= nums.length - 1) return true;
        if (nums.length == 2) return nums[0] >= 1;
        return tryToFindPath(nums, 0);
    }

    public static boolean tryToFindPath(int[] nums, int index) {
        int max;
        int last = 0;
        while (true) {
            if (index >= nums.length - 1) return true;
            if (index + nums[index] >= nums.length - 1) return true;
            max = Integer.MIN_VALUE;
            for (int i = 1; i <= nums[index]; i++) {
                if (index + i > nums.length - 1) return true;
                if (nums[index + i] >= max) {
                    max = nums[index + i];
                    last = index + i;
                }
            }
            if (max == 0 || max == Integer.MIN_VALUE) return false;
            index = last + max;
        }
    }

}
