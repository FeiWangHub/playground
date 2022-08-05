package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.ListNode;

/**
 * 移除链表元素
 * https://leetcode.cn/problems/remove-linked-list-elements/
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 */
public class E_LinkedList_RemoveElement_203 {

    /**
     * 100% 64%
     */
    public ListNode removeElements(ListNode head, int val) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {//找到
                if (pre == null) {//头节点
                    head = cur.next;
                    cur = cur.next;
                    continue;
                } else {//非头节点
                    pre.next = cur.next;
                    cur = cur.next;
                }
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return head;
    }

}
