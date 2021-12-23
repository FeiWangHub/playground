package com.fei.playground.algorithm;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/comments/
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class ReverseLinkList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode current = head;
        ListNode tempNext ;

        while(current != null){
            tempNext = current.next;
            current.next=pre;
            pre = current;
            current = tempNext;
        }
        return pre;
    }

    public ListNode reverseListNew(ListNode head) {

        ListNode lastReversed = null;
        ListNode currToReverse = head;
        ListNode nextToReverse = null;

        while(currToReverse != null){
            nextToReverse = currToReverse.next;
            currToReverse.next = lastReversed;
            lastReversed = currToReverse;
            currToReverse = nextToReverse;
        }

        return lastReversed;
    }

    public static void main(String[] args) {
        // [1,2,3,4,5]
    }
}
