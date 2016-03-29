package leetcode.problems_0_100.problem_94;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by whoosh on 3/30/16.
 */
public class Solution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        System.out.println(inorderTraversal(root));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        TreeNode next = root;
        ArrayList<Integer> result = new ArrayList<>(500);
        Deque<TreeNode> nodes = new LinkedList<>();
        nodes.add(null);
        do {
            while (next != null) {
                nodes.addLast(next);
                next = next.left;
            }
            root = nodes.pollLast();
            if (root == null) break;
            result.add(root.val);
            next = root.right;
        } while (!nodes.isEmpty());
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
