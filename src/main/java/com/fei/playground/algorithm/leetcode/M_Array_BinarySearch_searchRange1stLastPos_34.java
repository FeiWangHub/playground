package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 * 你必须设计并实现时间复杂度为O(log n)的算法解决此问题。
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class M_Array_BinarySearch_searchRange1stLastPos_34 {

    /**
     * 手撕二分法 100% 31%
     * 时间logN 空间1
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        //BS 找到第一个target的位置，然后while循环找到所有

        int lo = 0, hi = nums.length - 1;//左闭右闭
        int mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {//go left
                hi = mid - 1;
            } else {//go right
                lo = mid + 1;
            }
        }

        System.out.println("target1stIdx: " + lo);

        // not found
        if (lo < 0 || lo >= nums.length || nums[lo] != target) return res;

        res[0] = lo;
        while (lo <= nums.length - 2 && nums[lo + 1] == target) lo++;
        res[1] = lo;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }

}
