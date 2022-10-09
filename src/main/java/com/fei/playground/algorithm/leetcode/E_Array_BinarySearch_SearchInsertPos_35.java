package com.fei.playground.algorithm.leetcode;

/**
 * https://leetcode.cn/problems/search-insert-position/
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class E_Array_BinarySearch_SearchInsertPos_35 {

    /**
     * 手撕左右闭区间
     */
    public int searchInsert_manual(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;//左右闭区间
        int mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) {//go left
                hi = mid - 1;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    public int searchInsert(int[] nums, int target) {
        //笨解法1
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur > target && i == 0) {
                return 0;
            } else if (cur == target) {
                return i;
            } else if (i != nums.length - 1 && cur < target && target < nums[i + 1]) {
                return i + 1;
            }
        }
        return nums.length;
    }

    public int searchInsert2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public int searchInsert3(int[] nums, int target) {
        //二分法解法
        int left = 0;
        int right = nums.length - 1;

        int middle;
        int cur;
        while (left <= right) {
            middle = left + (right - left) / 2;
            cur = nums[middle];
            if (cur == target) {
                return middle;
            } else if (cur < target) {//find right
                left = middle + 1;
            } else if (cur > target) {//find left
                right = middle - 1;
            }
        }
        return right + 1;
    }

}
