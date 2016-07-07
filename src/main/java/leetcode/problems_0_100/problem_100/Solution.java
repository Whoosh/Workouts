package leetcode.problems_0_100.problem_100;

/**
 * Created by whoosh on 6/8/16.
 */
public class Solution {
    public static void main(String[] args) {

    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return p == null && q == null || p != null && q != null && q.val == p.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
