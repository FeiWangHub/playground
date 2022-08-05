package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.ListNode;

/**
 * 反转链表 II
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表。
 */
public class M_LinkedList_ReverseBetween_92 {

    /**
     * 100% 81.55%
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre = null;
        ListNode cur = head;
        int curIdx = 1;

        //1 找到节点  TODO如果头节点就是，怎么处理？
        while (cur != null && curIdx != left) {
            curIdx++;
            pre = cur;
            cur = cur.next;
        }

        //2 翻转，返还翻转部分的，首尾节点
        ListNode newTail = cur;
        ListNode newPre = null;
        ListNode newTemp = null;
        while (cur != null && curIdx <= right) { //寻找新的right
            newTemp = cur.next;
            cur.next = newPre;
            newPre = cur;
            cur = newTemp;
            curIdx++;
        }

        //3 回到节点
        if (pre != null) pre.next = newPre;
        newTail.next = cur;

        return (left == 1) ? newPre : head;
    }

}
