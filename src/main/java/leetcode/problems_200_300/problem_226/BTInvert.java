package leetcode.problems_200_300.problem_226;

/**
 * Created by whoosh on 3/30/16.
 */
public class BTInvert {

    public static void main(String[] args) {
        TreeNode top = new TreeNode(4);
        top.left = new TreeNode(2);
        top.right = new TreeNode(7);
        top.left.left = new TreeNode(1);
        top.left.right = new TreeNode(3);
        top.right.left = new TreeNode(6);
        top.right.right = new TreeNode(9);
        invertTree(top);
    }

    private static boolean LH;

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        LH = false;
        if (root.left != null) {
            LH = root.left.val >= root.val;
        } else if (root.right != null) {
            LH = root.right.val < root.val;
        }


        TreeNode inverted = new TreeNode(root.val);
        addAll(root, inverted);
        return inverted;
    }

    private static void addAll(TreeNode root, TreeNode inverted) {
        if (root == null) return;
        if (root.right != null)
            add(inverted, root.right.val);
        if (root.left != null)
            add(inverted, root.left.val);
        addAll(root.right, inverted);
        addAll(root.left, inverted);
    }

    private static void add(TreeNode node, int value) {
        if (node == null) return;
        if (!LH) {
            if (node.val < value) {
                if (node.left == null) node.left = new TreeNode(value);
                else add(node.left, value);
            } else {
                if (node.right == null) node.right = new TreeNode(value);
                else add(node.right, value);
            }
        }else {
            if (node.val > value) {
                if (node.left == null) node.left = new TreeNode(value);
                else add(node.left, value);
            } else {
                if (node.right == null) node.right = new TreeNode(value);
                else add(node.right, value);
            }
        }
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
