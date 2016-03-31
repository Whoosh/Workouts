package leetcode.problems_200_300.problem_203;

/**
 * Created by whoosh on 4/1/16.
 */
public class Solution {
    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        ListNode root = node;
        ListNode print = node;
        for (int i = 1; i <= 15; i++) {
            node.next = new ListNode(i % 3 == 0 ? 3 : i);
            node = node.next;
        }
        print(print);
        print(removeElements(root, 3));
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode root = head;
        ListNode prev = root;
        while (root.next != null) {
            if (root.val == val) {
                deleteNode(root);
                continue;
            }
            prev = root;
            root = root.next;
        }
        if (root.val == val) prev.next = null;
        if (prev.val == val) return null;
        return head;
    }

    public static ListNode deleteNode(ListNode node) {
        if (node == null) return node;
        if (node.next == null) return node;
        node.val = node.next.val;
        node.next = node.next.next;
        return node.next;
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
