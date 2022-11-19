package com.fei.playground.algorithm.leetcode;

/**
 * 977. 有序数组的平方
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 * https://leetcode.cn/problems/squares-of-a-sorted-array/
 */
public class E_Array_DoublePointer_SquaresOfSortedArray_977 {

    /**
     * O(n)双指针思路
     * 左右两边双指针，向中间靠拢，把当前最大的square放在尾部
     */
    public int[] sortedSquares(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
                ++left;
            } else {
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }

}
