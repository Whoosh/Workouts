package leetcode.problems_0_100.problem_75;

/**
 * Created by whoosh on 2/14/16.
 */
public class Solution {
    public static void main(String[] args) {
        sortColors(new int[]{0,0,0,0,0,0,2,1,1,1,1,2});
    }

    public static void sortColors(int[] nums) {
        int[] map = new int[3];
        for (int value : nums) map[value]++;
        int index = nums.length - 1;
        for (int i = map.length - 1; i >= 0; --i) {
            if (map[i] != 0) {
                for (int j = map[i]; j > 0; j--) nums[index--] = i;
            }
        }
    }
}
