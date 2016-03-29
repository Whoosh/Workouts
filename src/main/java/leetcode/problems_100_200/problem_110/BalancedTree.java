package leetcode.problems_100_200.problem_110;

/**
 * Created by whoosh on 3/29/16.
 */
public class BalancedTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        System.out.println(maxPath(root));
        System.out.println(isBalanced(root));
    }

    private static boolean balanced;

    public static boolean isBalanced(TreeNode root) {
        balanced = true;
        maxPath(root);
        return balanced;
    }

    private static int maxPath(TreeNode root) {
        if (root == null) return 0;
        final int l = maxPath(root.left);
        final int r = maxPath(root.right);
        if (Math.abs(l - r) > 1) balanced = false;
        return (l > r ? l : r) + 1;
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
