package com.future.round2;

import com.future.utils.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Created by xingfeiy on 11/17/17.
 */
public class Problem23 {
    /**
     * Analyze:
     * The problem is, we are going to find the minimum node in heads of each list, and again and again.
     * So we can use Priority Queue, we insert the heads into queue first, and pop the minimum one, and then insert the next of minimum one.
     *
     * Time Complexity: Priority Queue offer time complexity O(lgn) for insertion, and O(1) for poll out, so the
     * time complexity here is O(nlgn) where n is the number of nodes.
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length < 1) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>(){
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });

        for(ListNode node : lists) {
            if(node != null) pq.offer(node);
        }
        ListNode head = null;
        ListNode next = null;
        while(!pq.isEmpty()) {
            ListNode tmp = pq.poll();
            if(head == null) head = tmp;
            if(next != null) next.next = tmp;
            next = tmp;
            if(tmp.next != null) pq.offer(tmp.next);
        }
        return head;
    }


}
