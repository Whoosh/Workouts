package leetcode.problems_100_200.problem_173;

import java.util.Deque;
import java.util.LinkedList;

public class BSTIterator {

    private TreeNode node;
    private boolean back;
    private Deque<TreeNode> stack = new LinkedList<>();

    public BSTIterator(TreeNode node) {
        this.node = node;
        if (node != null) {
            stack.push(node);
        }
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