package leetcode.problem_11;

public class Solution {
    public static void main(String[] args) {
        int[] height = new int[]{1, 1, 1, 2, 3, 4, 4, 4, 1, 2, 5};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] h) {
        int maxFounded = 0;
        int start = 0;
        int end = h.length - 1;
        int b, n;
        while ((n = end - start) > 0) {
            b = h[h[start] < h[end] ? start++ : end--] * n;
            if (b > maxFounded) maxFounded = b;
        }
        return maxFounded;
    }
}
