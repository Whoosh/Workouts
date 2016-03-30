package leetcode.problems_200_300.problem_283;

import java.util.Arrays;

/**
 * Created by whoosh on 3/31/16.
 */
public class Solution {
    public static void main(String[] args) {
        int[] ints = {0, 1, 1, 1, 0, 0, 0, 5, 2, 1, 4, 2, 3, 0, 0, 1};
        int[] ints1 = {0, 1, 0};
        int[] ints2 = {0, 1};
        int[] ints3 = {1, 1};
        int[] ints4 = {1, 0};
        moveZeroes(ints);
        moveZeroes(ints1);
        moveZeroes(ints2);
        moveZeroes(ints3);
        moveZeroes(ints4);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(ints1));
        System.out.println(Arrays.toString(ints2));
        System.out.println(Arrays.toString(ints3));
        System.out.println(Arrays.toString(ints4));
    }

    public static void moveZeroes(int[] nums) {
        int d = 1;
        for (int i = 0, j; i < nums.length; i++) {
            if (nums[i] == 0) {
                j = i + d;
                while (j < nums.length && nums[j] == 0) {
                    ++d;
                    ++j;
                }
                if (j == nums.length) break;
                nums[i] = nums[j];
                nums[j] = 0;
            }
        }
    }
}
