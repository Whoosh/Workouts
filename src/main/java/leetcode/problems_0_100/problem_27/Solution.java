package leetcode.problems_0_100.problem_27;

/**
 * Created by whoosh on 4/1/16.
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(removeElement(new int[]{1, 2, 3, 4, 4, 4, 5, 5, 1, 2, 3, 2}, 1));
    }

    public static int removeElement(int[] nums, int val) {
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
