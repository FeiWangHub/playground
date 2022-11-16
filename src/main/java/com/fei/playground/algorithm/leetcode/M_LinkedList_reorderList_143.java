package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.ListNode;

import java.util.ArrayList;

/**
 * 143. 重排链表
 * 原始链表 L0 → L1 → … → Ln - 1 → Ln
 * 重排之后 L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * https://leetcode.cn/problems/reorder-list/
 */
public class M_LinkedList_reorderList_143 {

    /**
     * 手撕原地无额外空间 100% 20%
     * 1. 快慢指针找到中间节点
     * 2. 反转后半段链表
     * 3. 合并前半段链表和后半段链表
     */
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow.next;

        //1. 快慢指针找到中间节点; 奇数个，slow会停在中点；偶数个，slow会停在中间左边
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //2. 反转后半段链表(保证前半段比后半段长)
        ListNode secondHead = reverseLinkedList(slow.next);
        slow.next = null;

        //3. 合并前半段链表和后半段链表
        while (head != null && secondHead != null) {
            slow = head.next;
            fast = secondHead.next;

            secondHead.next = head.next;
            head.next = secondHead;

            head = slow;
            secondHead = fast;
        }
    }

    public ListNode reverseLinkedList(ListNode head) {
        ListNode pre = null;
        ListNode current = head;
        ListNode tempNext;

        while (current != null) {
            tempNext = current.next;
            current.next = pre;
            pre = current;
            current = tempNext;
        }
        return pre;
    }

    /**
     * 我的第一版 ArrayList 34% 91%
     */
    public void reorderList_arrayList(ListNode head) {
        //思路1 放入array方便索引位置
        ArrayList<ListNode> list = new ArrayList<>();

        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }

        //偶数 4/2 = 2
        int headIdx = 0;
        int tailIdx = list.size() - 1;
        while (headIdx <= tailIdx) {
            //System.out.println("current head:" + headIdx + " tail:" + tailIdx);
            if (headIdx == tailIdx - 1 || headIdx == tailIdx) {//偶数和奇数处理
                list.get(tailIdx).next = null;
                return;
            }
            list.get(tailIdx).next = list.get(headIdx).next;
            list.get(headIdx).next = list.get(tailIdx);
            headIdx++;
            tailIdx--;
        }
    }

}
