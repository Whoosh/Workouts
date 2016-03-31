package leetcode.problems_200_300.problem_237;

/**
 * Created by whoosh on 3/31/16.
 */
public class Solution {

    public static void main(String[] args) {
        ListNode node = new ListNode(0);
        ListNode root = node;
        ListNode remove = node;
        for (int i = 1; i <= 3; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        print(root);
        print(remove);
        deleteNode1(remove);
        print(root);
    }

    public static void deleteNode1(ListNode node) {
        if (node == null) return;
        if (node.next == null) return;
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void deleteNode2(ListNode node) {
        if (node == null) return;
        if (node.next == null) return;
        if (node.next.next == null) {
            node.val = node.next.val;
            node.next = null;
            return;
        }
        do {
            System.out.println(node.val);
            node.val = node.next.val;
            node = node.next;
        } while (node.next.next != null);
        deleteNode2(node);
    }

    private static void print(ListNode node) {
        ListNode next = node;
        while (next != null) {
            System.out.print(next.val + " ");
            next = next.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
