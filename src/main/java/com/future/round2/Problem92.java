package com.future.round2;

import com.future.utils.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/description/
 *
 Reverse a linked list from position m to n. Do it in-place and in one-pass.

 For example:
 Given 1->2->3->4->5->NULL, m = 2 and n = 4,

 return 1->4->3->2->5->NULL.

 Note:
 Given m, n satisfy the following condition:
 1 ≤ m ≤ n ≤ length of list.


 * Created by xingfeiy on 12/10/17.
 */
public class Problem92 {
    /**
     * Analyze:
     *  1->2->3->4->5->NULL, m = 1 and n = 4,
     *  4->3->2->1->5->NULL
     *  1->2->3->4->5->NULL, m = 1 and n = 5
     *  5->4->3->2->1->NULL
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preNode = dummy;
        for(int i = 1; i < m; i++) preNode = preNode.next;
        ListNode firstNode = preNode.next;
        ListNode nextNode = firstNode.next;
        for(int i = 0; i < n - m; i++) {
            firstNode.next = nextNode.next;
            nextNode.next = preNode.next;
            preNode.next = nextNode;
            nextNode = firstNode.next;
        }
        return dummy.next;
    }
}
