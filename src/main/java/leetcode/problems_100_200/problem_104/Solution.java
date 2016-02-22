package leetcode.problems_100_200.problem_104;

/**
 * Created by whoosh on 2/20/16.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        TreeNode root = treeNode;
        for (int i = 0; i < 10; i++) {
            treeNode.left = new TreeNode(i);
            if ((i & 1) == 0)
                treeNode.right = new TreeNode(i);
            treeNode = treeNode.left;
        }
        System.out.println(maxDepth(root));
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        final int l = maxDepth(root.left);
        final int r = maxDepth(root.right);
        if (l < r) return r + 1;
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
