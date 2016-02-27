package leetcode.problems_100_200.problem_111;

/**
 * Created by whoosh on 2/20/16.
 */
public class Solution {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        TreeNode root = treeNode;
        System.out.println(minDepth(root));
    }

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        final int l = minDepth(root.left);
        final int r = minDepth(root.right);
        if (l > r) return r + 1;
        return l + 1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
