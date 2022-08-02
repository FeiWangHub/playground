package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.ListNode;
import com.fei.playground.algorithm.TreeNode;

/**
 * 两数相加
 * 给你两个'非空 的链表，表示两个非负的整数。它们每位数字都是按照'逆序'的方式存储的，并且每个节点只能存储'一位'数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0'开头。
 * 链接：https://leetcode.cn/problems/add-two-numbers
 */
public class M_LinkedList_AddTwoNumber_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = new ListNode();
        ListNode curNode = root;

        int remaining = 0;
        do {
            if (l1 != null) {
                remaining += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                remaining += l2.val;
                l2 = l2.next;
            }

            if (remaining < 10) {
                curNode.val = remaining;
                remaining = 0;
            } else {
                curNode.val = remaining % 10;
                remaining = 1;
            }

            if (l1 != null || l2 != null || remaining != 0) {
                ListNode n = new ListNode();
                curNode.next = n;
                curNode = n;
            }

        } while (l1 != null || l2 != null || remaining != 0);

        return root;
    }

}
