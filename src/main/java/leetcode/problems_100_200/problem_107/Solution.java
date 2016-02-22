package leetcode.problems_100_200.problem_107;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by whoosh on 2/20/16.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        levelOrderBottom(root).forEach(System.out::println);
    }

    private static List<List<Integer>> list;
    private static boolean[] map;

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        list = new LinkedList<>();
        map = new boolean[1000];
        fillListFromTree(root, 0);
        Collections.reverse(list);
        return list;
    }

    public static void fillListFromTree(TreeNode node, int lvl) {
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
