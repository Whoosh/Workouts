package leetcode.problems_100_200.problem_108;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by whoosh on 3/30/16.
 */
public class USolution {

    private static List<List<Integer>> list;
    private static boolean[] map;

    public static void main(String[] args) {
        System.out.println(levelOrder(sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7})));
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        for (int i = nums.length - 2; i > 0; i--) {
            add(root, nums[i]);
        }
        return root;
    }

    private static void add(TreeNode tree, int value) {
        if (tree == null) return;
        if (tree.val < value && tree.right == null) {
            tree.right = new TreeNode(value);
            return;
        } else if (tree.val >= value && tree.left == null) {
            tree.left = new TreeNode(value);
            return;
        }
        if (tree.val > value) {
            add(tree.left, value);
        } else {
            add(tree.right, value);
        }
        if (maxPath(tree.left) == 1) {
            if (tree.right != null)
                if (maxPath(tree.right.left) <= maxPath(tree.right.right)) {
                    minTurnLeft(tree);
                }
        }
        if (maxPath(tree.right) == 1) {
            if (tree.left != null)
                if (maxPath(tree.left.left) >= maxPath(tree.left.right)) {
                    minTurnRight(tree);
                }
        }
        if (maxPath(tree.left) == 1) {
            if (tree.right != null)
                if (maxPath(tree.right.right) < maxPath(tree.left.left)) {
                    deepLeftTurn(tree);
                }
        }
        if (maxPath(tree.right) == 1) {
            if (tree.left != null)
                if (maxPath(tree.left.right) > maxPath(tree.left.left)) {
                    deepRightTurn(tree);
                }
        }
    }

    private static TreeNode minTurnLeft(TreeNode a) {
        final TreeNode b = a.right;
        a.right = b.left;
        b.left = a;
        return b;
    }

    private static TreeNode minTurnRight(TreeNode a) {
        final TreeNode b = a.right;
        a.left = b.right;
        b.right = a;
        return b;
    }

    private static TreeNode deepLeftTurn(TreeNode a) {
        final TreeNode b = a.right;
        final TreeNode c = b.left;
        a.right = c.left;
        b.left = c.right;
        c.left = a;
        c.right = b;
        return c;
    }

    private static TreeNode deepRightTurn(TreeNode a) {
        final TreeNode b = a.left;
        final TreeNode c = b.right;
        b.right = c.left;
        a.left = c.right;
        c.left = b;
        c.right = a;
        return c;
    }

    private static int maxPath(TreeNode root) {
        if (root == null) return 0;
        final int l = maxPath(root.left);
        final int r = maxPath(root.right);
        return (l > r ? l : r) + 1;
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