package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.ListNode;

import java.util.Stack;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每k个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/
 */
public class H_LinkedList_ReverseNodesInKGroup_25 {

    /**
     * 暴力手撕Stack+While 7.2% 16%
     * 一遍过 Yeah
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) return head;

        ListNode newHead = null;
        Stack<ListNode> stk = new Stack<>();
        ListNode next = head;

        ListNode preOfCurK = null;//当前这K个节点的pre，上一个K系列的last
        ListNode temp = null;

        //循环整个链表
        while (next != null) {
            //尝试凑齐k个元素
            while (stk.size() != k && next != null) {
                stk.add(next);
                next = next.next;
            }
            if (stk.size() == k) {//凑齐了
                //开始翻转; 弹出来的第一个，是本K组的头；剩下stk弹出来每一个出来点，都是上一个的child
                temp = stk.pop();
                if (preOfCurK == null) newHead = temp;
                if (preOfCurK != null) preOfCurK.next = temp;
                while (!stk.isEmpty()) {
                    temp.next = stk.pop();
                    temp = temp.next;
                }
                temp.next = next;//指向下一个k group
                preOfCurK = temp;
            } else {//凑不齐
                break;
            }
        }

        return newHead == null ? head : newHead;
    }

    /**
     * 官方无Stack模拟 100% 20%
     */
    public ListNode reverseKGroup_officialMock(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }

}
