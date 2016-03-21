package leetcode.problems_300_400.problem_327;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by whoosh on 3/20/16.
 */
public class Solution {

    private static int[] cache = new int[10000];

    public static void main(String[] args) {
        System.out.println(countRangeSum(new int[]{-2,5,1},-2,2));
        final int size = 10000;
        int[] data = new int[size];
        Random random = new Random(1111);
        int max = random.nextInt(size * 2) - size;
        int min = random.nextInt(Math.abs(max * 2)) - size;
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(size * 2) - size;
        }
        System.out.println(Arrays.toString(data));
        System.out.println(min);
        System.out.println(max);
        System.out.println(countRangeSum(data, min, max));
    }

    public static int countRangeSum(int[] nums, int lower, int upper) {
        int result = 0;
        int length = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= lower && nums[i] <= upper) result++;
            cache[i] = nums[i];
        }
        int i, j;
        while (length < nums.length) {
            i = 0;
            j = length;
            while (j < nums.length) {
                cache[i] = cache[i] + nums[j];
                if (cache[i] >= lower && cache[i] <= upper) result++;
                j++;
                i++;
            }
            length++;
        }
        return result;
    }
}
