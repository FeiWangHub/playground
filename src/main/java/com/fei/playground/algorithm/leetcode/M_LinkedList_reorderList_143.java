package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 143. 重排链表
 * 原始链表 L0 → L1 → … → Ln - 1 → Ln
 * 重排之后 L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * https://leetcode.cn/problems/reorder-list/
 */
public class M_LinkedList_reorderList_143 {

    /**
     * 原地翻转
     * 1. 快慢指针找到中间节点
     * 2. 反转后半段链表
     * 3. 合并前半段链表和后半段链表
     */
    public void reorderList(ListNode head) {

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
