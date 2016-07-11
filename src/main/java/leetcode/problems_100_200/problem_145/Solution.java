package leetcode.problems_100_200.problem_145;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(5);
        node.left.right = new TreeNode(4);
        node.left.right.right = new TreeNode(1);
        postorderTraversal(node).forEach(System.out::println);
    }

    public static List<Integer> postorderTraversal(TreeNode node) {
        final List<Integer> result = new LinkedList<>();
        final Deque<TreeNode> stack = new LinkedList<>();
        TreeNode lastRootNode = null;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.peek().right == null || lastRootNode == stack.peek().right) {
                result.add(stack.peek().val);
                lastRootNode = stack.pop();
            } else
                node = stack.peek().right;
        }
        return result;
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