package leetcode.problem_4;

import java.util.Arrays;

/**
 * Created by whoosh on 10/25/15.
 */
public class Solution {

    public static void main(String[] args) {
        int[] first = new int[]{1,2,3,4,5,6};
        int[] second = new int[]{4};

        double medianSortedArrays = new Solution().findMedianSortedArrays2(first, second);
    //    double test = new Solution().slowMedianFinder(first, second);
    //    double recS = new Solution().findMedianSortedArrays(second, first);
        System.out.println("fast Method - " + medianSortedArrays);
    //    System.out.println("slow Method - " + test);
     //   System.out.println("recS Method - " + recS);
    }

    public double slowMedianFinder(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        for (int i = a.length, k = 0; i < c.length; i++, k++) c[i] = b[k];
        Arrays.sort(c);
        System.out.println(Arrays.toString(c));
        if (c.length <= 4) return slowMedianFinderForLittleSizeArray(c);
        if ((c.length & 1) == 0) return ((double) c[c.length / 2] + c[c.length / 2 - 1]) / 2;
        else return c[c.length / 2];
    }

    public double slowMedianFinderForLittleSizeArray(int[] c) {
        if (c.length == 4) return ((double) c[1] + c[2]) / 2;
        if (c.length == 3) return c[1];
        if (c.length == 2) return ((double) c[0] + c[1]) / 2;
        if (c.length == 1) return c[0];
        else return 0;
    }

    public double findMedianSortedArrays(int[] first, int[] second) {
        System.out.println(Arrays.toString(second));
        System.out.println(Arrays.toString(first));
        double result = 0;
//        if (first.length < second.length) result = found(first, second);
//        else result = found(second, first);
        result = found(first, second);
        return result;
    }

    private double found(int[] first, int[] second) {

        return findMedian(first, second, 0, first.length, 0, second.length);
    }

    private double findMedian(int[] first, int[] second, int f_start, int f_end, int s_start, int s_end) {

        int firstLen = f_end - f_start;
        int secondLen = s_end - s_start;

        int firstMiddle = f_start + firstLen / 2;
        int secondMiddle = s_start + secondLen / 2;

        if (firstLen + secondLen <= 2) {
            int[] a = new int[firstLen];
            int[] b = new int[secondLen];
            for (int i = s_start, z = 0; i < s_end; i++, z++) b[z] = second[i];
            for (int i = f_start, z = 0; i < f_end; i++, z++) a[z] = first[i];
            System.out.println(Arrays.toString(a) + " | " + Arrays.toString(b));
            return slowMedianFinder(a, b);
        }

        System.out.println(firstMiddle + " " + secondMiddle);

        if (checkAngGet(first, firstMiddle) == checkAngGet(second, secondMiddle)) {
            return checkAngGet(first, firstMiddle);
        }

        if (checkAngGet(first, firstMiddle) > checkAngGet(second, secondMiddle) && checkAngGet(first, firstMiddle) < checkAngGet(second, secondMiddle + 1)) {
            //     System.out.println("A");
            return (double) (checkAngGet(first, firstMiddle - 1) + checkAngGet(second, secondMiddle)) / 2;
        }


        if (checkAngGet(first, firstMiddle) > checkAngGet(second, secondMiddle))
            return findMedian(first, second, f_start + 1, firstMiddle, secondMiddle, s_end - 1);
        if (checkAngGet(first, firstMiddle) < checkAngGet(second, secondMiddle))
            return findMedian(first, second, firstMiddle + 1, f_end, s_start, secondMiddle - 1);
        return 0;
    }

    private int checkAngGet(int[] A, int i) {
        return (i < 0) ? Integer.MIN_VALUE : ((i >= A.length) ? Integer.MAX_VALUE : A[i]);
    }

    private double calcEven(int[] nums1, int[] nums2, int i, int j) {
        return (double) (Math.min(checkAngGet(nums1, i), checkAngGet(nums2, j)) + Math.max(checkAngGet(nums1, i - 1), checkAngGet(nums2, j - 1))) / 2;
    }

    private double calcOdd(int[] nums1, int[] nums2, int i, int j) {
        return Math.min(checkAngGet(nums1, i), checkAngGet(nums2, j));
    }

    private double middleForSimpleArray(int[] a) {
        return (a.length & 1) == 0 ? (double) (a[a.length / 2] + a[a.length / 2 - 1]) / 2 : a[a.length / 2];
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        boolean even = ((nums1.length + nums2.length) & 1) == 0;
        int i, j, middleOfBoth = (nums1.length + nums2.length) / 2;
        int lBound = 0, hBound = Math.min(middleOfBoth, nums1.length);
        if (nums1.length == 0) return middleForSimpleArray(nums2);
        if (nums2.length == 0) return middleForSimpleArray(nums1);
        while (true) {
            i = lBound + (hBound - lBound) / 2;
            j = middleOfBoth - i;
            if (minOrEquals(nums2, nums1, j - 1, i)) {
                if (minOrEquals(nums1, nums2, i - 1, j))
                    return even ? calcEven(nums1, nums2, i, j) : calcOdd(nums1, nums2, i, j);
                else hBound = i - 1;
            } else lBound = i + 1;
        }
    }

    private boolean minOrEquals(int[] nums1, int[] nums2, int i, int j) {
        return checkAngGet(nums2, j) >= checkAngGet(nums1, i);
    }
}
