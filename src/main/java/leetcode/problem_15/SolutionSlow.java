package leetcode.problem_15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by whoosh on 2/11/16.
 */
public class SolutionSlow {

    private static final MPredicate PREDICATE = (a, b, c) -> a + c + b == 0;

    public static List<List<Integer>> threeSum(int[] nums) {
        HashSet<List<Integer>> result = new HashSet<>(100);
        if (nums.length < 3) return new ArrayList<>();
        Arrays.sort(nums);
        int firstPositiveIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                firstPositiveIndex = i;
                break;
            }
        }
        ArrayList<Integer> preResult;
        if (firstPositiveIndex == -1) return new ArrayList<>();
        for (int i = nums.length - 1; i >= firstPositiveIndex; --i) {
            final int rightElement = nums[i];
            for (int j = 0; j < firstPositiveIndex - 1; j++) {
                for (int k = j + 1; k < firstPositiveIndex; k++) {
                    if (PREDICATE.actual(nums[j], nums[k], rightElement)) {
                        preResult = new ArrayList<>();
                        preResult.add(nums[j]);
                        preResult.add(nums[k]);
                        preResult.add(rightElement);
                        if (result.contains(preResult)) break;
                        result.add(preResult);
                    }
                    if (rightElement > -(nums[j] + nums[k])) break;
                }
            }
        }
        for (int i = 0; i < firstPositiveIndex; i++) {
            final int leftElement = nums[i];
            for (int j = firstPositiveIndex; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (PREDICATE.actual(leftElement, nums[k], nums[j])) {
                        preResult = new ArrayList<>();
                        preResult.add(leftElement);
                        preResult.add(nums[j]);
                        preResult.add(nums[k]);
                        if (result.contains(preResult)) break;
                        result.add(preResult);
                    }
                    if (-leftElement < nums[j] + nums[k]) break;
                }
            }
        }
        return new ArrayList<>(result);
    }

    private interface MPredicate {
        boolean actual(int a, int b, int c);
    }
}
