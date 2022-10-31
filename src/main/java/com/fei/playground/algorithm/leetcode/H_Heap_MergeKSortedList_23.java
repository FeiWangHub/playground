package com.fei.playground.algorithm.leetcode;

import com.fei.playground.algorithm.ListNode;

import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class H_Heap_MergeKSortedList_23 {

    /**
     * 手撕 6.5% 52.9%
     * 暴力循环 O(lists长度*总节点个数)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0 || (lists.length == 1 && lists[0] == null)) return null;

        //每次循环Node，选出最小的节点放进去？
        ListNode head = null, cur = null;

        int minVal = Integer.MAX_VALUE;
        int minIdx = Integer.MAX_VALUE;

        while (true) {
            minVal = Integer.MAX_VALUE;
            minIdx = Integer.MAX_VALUE;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) continue;
                if (lists[i].val < minVal) {
                    minVal = lists[i].val;
                    minIdx = i;
                }
            }
            if (minVal == Integer.MAX_VALUE) break;//全部为空

            if (cur == null) {//第一个节点
                cur = new ListNode(minVal);
                head = cur;
            } else {
                cur.next = new ListNode(minVal);
                cur = cur.next;
            }
            lists[minIdx] = lists[minIdx].next;
        }

        return head;
    }

    /**
     * 官方PriorityQueue 71% 82%
     * 用容量为K的最小堆优先队列，把链表的头结点都放进去，然后出队当前优先队列中最小的，挂上链表，
     * 然后让出队的那个节点的下一个入队，再出队当前优先队列中最小的，直到优先队列为空。
     */
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists_byHEAP(ListNode[] lists) {
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

    /**
     * 官方分支两两合并 100% 15%
     * 将所有链表配对，两两合并；然后重复此步骤，直到最后
     */
    public ListNode mergeKLists_byDivideAndMerge(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

}
