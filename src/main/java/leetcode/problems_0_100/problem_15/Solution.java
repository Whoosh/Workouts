package leetcode.problems_0_100.problem_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by whoosh on 2/11/16.
 */

public class Solution {

    public static void main(String[] args) {
        for (List<Integer> subList : threeSum(new int[]{-2, 0, 1, 1, 2})) {
            System.out.println(subList);
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1, k = nums.length - 1; j < k; ) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    result.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else if (nums[i] + nums[j] + nums[k] > 0) k--;
                else j++;
            }
        }
        return result;
    }
}
