package leetcode.problems_100_200.problem_144;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;


public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left = new TreeNode(1);
        node.left.right = new TreeNode(4);
        System.out.println(preorderTraversal(node));
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Deque<TreeNode> lastNodes = new LinkedList<>();
        while (!lastNodes.isEmpty() || root != null) {
            if (root != null) {
                result.add(root.val);
                lastNodes.add(root);
                root = root.left;
            } else {
                root = lastNodes.pollLast().right;
            }
        }
        return result;
    }
}