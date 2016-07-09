package leetcode.problems_0_100.problem_98;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by whoosh on 7/6/16.
 */

public class Solution {

    private TreeNode node;
    private boolean back;
    private Deque<TreeNode> stack = new LinkedList<>();

    public boolean isValidBST(TreeNode root) {
        this.node = root;
        if (node == null) {
            return true;
        }
        stack.push(node);
        int prev = next(), current;
        while (hasNext()) {
            current = next();
            if (current < prev) return false;
            prev = current;
        }
        return true;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public int next() {
        node = stack.pop();
        if (back && node.right == null) return node.val;
        if (back) {
            stack.push(node.right);
            back = false;
            return node.val;
        }
        while (node.left != null) {
            stack.push(node);
            node = node.left;
        }
        if (node.right != null) stack.push(node.right);
        else back = true;
        return node.val;
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