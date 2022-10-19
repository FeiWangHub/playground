package com.fei.playground.algorithm.leetcode;

import java.util.PriorityQueue;

public class M_Heap_QuickSort_findKthLargest_215 {

    /**
     * 最小堆heap解法 26% 19%
     * 时间 O(nlogk) 空间o(k)
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> a - b);
        for (int x : nums) {
            if (q.size() < k || q.peek() < x) q.add(x);
            if (q.size() > k) q.poll();
        }
        return q.peek();
    }

    /**
     * 快速排序 TODO
     */
    public int findKthLargest_qsort(int[] nums, int k) {
        int len = nums.length;
        qSort(nums, 0, len - 1);
        return nums[k - 1];
    }

    void qSort(int[] nums, int start, int end) {
        //1第一个数为基准值，大的放左边，小的放右边
        int base = nums[start];
        int left = start;
        int right = end;

        while (left < right) {
            //从左向右寻找第一个大于base的
            while (left < right && nums[right] < base) {
                right--;
            }
            //从右向左寻找第一个小于base的
            while (left < right && nums[left] > base) {
                left++;
            }
            if (nums[left] == nums[right] && left < right) {
                left++;
            } else {
                swap(nums, left, right);
            }
        }

        if (left - 1 > start) {
            qSort(nums, start, left - 1);
        }

        if (right + 1 < end) {
            qSort(nums, right + 1, end);
        }

    }


    int findByQuickSort(int[] nums, int k, int start, int end) {
        //1第一个数为基准值，大的放左边，小的放右边
        int base = nums[start];
        int l = start;
        int r = end;
        while (l < r) {
            //从左向右寻找第一个大于base的
            while (r < l && nums[r] < base) {
                r--;
            }
            //从右向左寻找第一个小于base的
            while (r < l && nums[l] > base) {
                l++;
            }
            if (l < r && nums[r] == nums[l]) {
                l++;
            } else {
                swap(nums, l, r);
            }
        }

        if (l == k) {
            return nums[k];
        } else if (l - 1 < start) {//向右边找
            return findByQuickSort(nums, k, l + 1, end);
        } else if (r + 1 < end) {//向左边找
            return findByQuickSort(nums, k, start, l - 1);
        }
        return nums[k];
    }

    void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
