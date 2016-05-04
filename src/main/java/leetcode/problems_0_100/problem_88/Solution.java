package leetcode.problems_0_100.problem_88;

/**
 * Created by whoosh on 4/6/16.
 */
public class Solution  {

    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length == 0) return;
        int end = m + n - 1;
        m--;
        n--;
        while (end >= 0) {
            while (m >= 0 && nums1[m] >= nums2[n]) nums1[end--] = nums1[m--];
            if (m < 0) break;
            while (n >= 0 && nums2[n] >= nums1[m]) nums1[end--] = nums2[n--];
            if (n < 0) break;
        }
        while (n >= 0) nums1[end--] = nums2[n--];
    }

}
