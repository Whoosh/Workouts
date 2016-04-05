package leetcode.problems_100_200.problem_147;

/**
 * Created by whoosh on 4/5/16.
 */
public class Solution {
    public static void main(String[] args) {
        ListNode node = new ListNode(5);

        print(node);
        print(insertionSortList(node));
    }

    private static void print(ListNode node) {
        ListNode next = node;
        while (next != null) {
            System.out.print(next.val + " ");
            next = next.next;
        }
        System.out.println();
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) return head;
        ListNode newListNode = new ListNode(0);
        ListNode newListNodeHead = newListNode;
        ListNode minNode = head;
        while (head.next != null) {
            ListNode next = head;
            int min = Integer.MAX_VALUE;
            while (next != null) {
                if (next.val < min) {
                    min = next.val;
                    minNode = next;
                }
                next = next.next;
            }
            if (minNode.next != null) {
                minNode.val = minNode.next.val;
                minNode.next = minNode.next.next;
            } else {
                if (head.next != null) {
                    ListNode wha = head;
                    while (wha.next.next != null) {
                        wha = wha.next;
                    }
                    wha.next = null;
                }
            }
            newListNode.val = min;
            newListNode.next = new ListNode(0);
            newListNode = newListNode.next;
        }
        newListNode.val = head.val;
        return newListNodeHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
