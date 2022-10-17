package com.fei.playground.algorithm.leetcode;

/**
 * https://leetcode.cn/problems/binary-search/
 * 二分法查找 (前提条件是，排序的，并且无重复数字)
 * 给定一个n个元素有序的（升序）整型数组nums 和一个目标值target ，写一个函数搜索nums中的 target，如果目标值存在返回下标，否则返回 -1。
 */
public class E_Array_BinarySearch_704 {

    /**
     * 左闭右闭的区间里，**也就是[left, right]
     * 结束时left和right位置交叉
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (right + left) / 2;//中点为中间偏左的位置
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {//向右走
                left = mid + 1;
            } else {//向左走
                right = mid - 1;
            }

        }
        return -1;
    }

    /**
     * 左闭右开区间
     * 结束时 left = right?
     */
    public int search_2(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return -1;
    }

}
