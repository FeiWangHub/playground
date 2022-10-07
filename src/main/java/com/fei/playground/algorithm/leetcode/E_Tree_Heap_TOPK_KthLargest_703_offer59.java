package com.fei.playground.algorithm.leetcode;

import java.util.PriorityQueue;

/**
 * 703 数据流中的第 K 大元素
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 *
 * 请实现 KthLargest类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *
 * https://leetcode.cn/problems/kth-largest-element-in-a-stream/
 *
 * 心得：topK类题目，都可以考虑使用Heap来解题(PriorityQueue默认小顶堆)
 * heap叫堆，也叫优先队列
 * (这个题也可以用冒泡排序解，也可以用排序+截取)
 */
public class E_Tree_Heap_TOPK_KthLargest_703_offer59 {

    /**
     * 官方小顶堆 96% 22%
     */
    PriorityQueue<Integer> pq;
    int k;

    public void kthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }

}
