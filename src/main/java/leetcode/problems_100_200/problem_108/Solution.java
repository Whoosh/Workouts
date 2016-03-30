package leetcode.problems_100_200.problem_108;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by whoosh on 3/30/16.
 */
public class Solution {

    private static List<List<Integer>> list;
    private static boolean[] map;
    private static int index;

    public static void main(String[] args) {
        System.out.println(levelOrder(sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7})));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        index = 0;
        return function(nums, 0, len - 1);
    }

    public static TreeNode function(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        final TreeNode left = function(nums, start, mid - 1);
        final TreeNode tree = new TreeNode(nums[index]);
        tree.left = left;
        tree.right = function(nums, mid + 1, end);
        return tree;
    }

    private static void insert(TreeNode tree, int value) {
        if (tree == null) return;
        if (tree.val < value && tree.right == null) {
            tree.right = new TreeNode(value);
            return;
        } else if (tree.val >= value && tree.left == null) {
            tree.left = new TreeNode(value);
            return;
        }
        if (tree.val > value) {
            insert(tree.left, value);
        } else {
            insert(tree.right, value);
        }
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        list = new LinkedList<>();
        map = new boolean[1000];
        fillListFromTree(root, 0);
        return list;
    }

    private static void fillListFromTree(TreeNode node, int lvl) {
        if (node == null) return;
        List<Integer> v;
        if (map[lvl]) v = list.get(lvl);
        else v = new LinkedList<>();
        v.add(node.val);
        if (map[lvl]) list.set(lvl, v);
        else list.add(v);
        map[lvl] = true;
        lvl++;
        fillListFromTree(node.left, lvl);
        fillListFromTree(node.right, lvl);
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