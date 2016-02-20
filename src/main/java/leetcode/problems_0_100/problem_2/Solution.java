package leetcode.problems_0_100.problem_2;

/**
 * Created by whoosh on 10/24/15.
 *
 * https://leetcode.com/problems/add-two-numbers/
 *
 * Seems it can be faster with int instead of long and group of int for huge values, but there is no really huge values in tests on service;
 */

public class Solution {

    public static void main(String[] args) {
        ListNode first, second;
        first = new ListNode(1);
        first.next = new ListNode(9);
        first.next.next = new ListNode(9);

        second = new ListNode(9);
        second.next = new ListNode(1);

        ListNode listNode = new Solution().addTwoNumbers(first, second);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long b, result = 0, index = 1;
        boolean gotSmt = false;
        while (l1 != null || l2 != null) {
            b = 0;
            if (l1 != null) b += l1.val;
            if (l2 != null) b += l2.val;
            if (gotSmt) b++;
            if (b < 10) {
                result += b * (index);
                gotSmt = false;
            } else {
                gotSmt = true;
                result += (b % 10) * index;
            }
            index *= 10;
            if (l2 != null) l2 = l2.next;
            if (l1 != null) l1 = l1.next;
        }
        ListNode resultList = null;
        if (gotSmt) resultList = new ListNode(1);
        for (index /= 10; index != 0; index /= 10) {
            ListNode next = resultList;
            resultList = new ListNode((int) (result / index));
            resultList.next = next;
            result -= resultList.val * index;
        }
        return resultList;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}