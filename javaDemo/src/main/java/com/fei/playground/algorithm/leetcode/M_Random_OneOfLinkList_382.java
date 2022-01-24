package com.fei.playground.algorithm.leetcode;

import java.util.Random;

/**
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * 实现 Solution 类：
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 *
 * https://leetcode-cn.com/problems/linked-list-random-node/submissions/
 */
public class M_Random_OneOfLinkList_382 {

    ListNode head;

    /**
     * 我写的第一版 水塘抽样法 击败61 77%
     */
    public int getRandom(ListNode head) {
        this.head = head;
        Random r = new Random();

        ListNode curNode = head;
        int result = head.val;
        float total = 1;

        while (curNode != null) {
            float rand = r.nextFloat();
            System.out.println("rand:" + rand + " total:" + 1 / total);
            if (rand < 1 / total) {
                result = curNode.val;
            }

            total++;
            curNode = curNode.next;
        }
        return result;
    }

    public static class ListNode {
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

}
