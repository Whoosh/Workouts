package leetcode.problems_0_100.problem_26;

import java.util.Arrays;

/**
 * Created by whoosh on 4/1/16.
 */
public class Solution {
    public static void main(String[] args) {
        int[] ints = {0,0,0,0,1,2,3,4,5};
        int size = removeDuplicates(ints);
        for (int i = 0; i < size; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();
        System.out.println(Arrays.toString(ints));
    }

    public static int removeDuplicates(int[] nums) {
        int before = 0, p = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1 && nums[i] != Integer.MAX_VALUE; i++) {
            if (nums[i] == nums[i + 1]) p = nums[i++];
            before = i;
            while (i < nums.length && nums[i] == p) nums[i++] = Integer.MAX_VALUE;
            if (before != i) i--;
        }
        return moveTrash(nums, Integer.MAX_VALUE);
    }

    public static int moveTrash(int[] nums, int val) {
        if (nums == null) return 0;
        int c = 0;
        for (int i = 0, d = 1, j; i < nums.length; i++) {
            if (nums[i] == val) {
                j = i + d;
                while (j < nums.length && nums[j] == val) {
                    ++d;
                    ++j;
                }
                c = d;
                if (j == nums.length) break;
                nums[i] = nums[j];
                nums[j] = val;
            }
        }
        return nums.length - c;
    }
}
