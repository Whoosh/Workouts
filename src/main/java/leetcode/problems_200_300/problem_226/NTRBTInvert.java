package leetcode.problems_200_300.problem_226;

/**
 * Created by whoosh on 3/30/16.
 */
public class NTRBTInvert {

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


    public static TreeNode invertTree(TreeNode root) {
        TreeNode side = root;
        TreeNode b;
        while (side != null) {
            b = side.left;
            side.left = side.right;
            side.right = b;
            invertTree (side.left);
            side = side.right;
        }
        return root;
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
