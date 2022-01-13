package com.fei.playground.algorithm.leetcode;

/**
 * 有序数组 二分法查找
 * https://leetcode-cn.com/problems/binary-search/
 */
public class E_Search_BinarySearch_704 {

    class Solution {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                int num = nums[mid];
                if (num == target) {
                    return mid;
                } else if (num > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            return -1;
        }
    }

}
