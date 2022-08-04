package com.fei.playground.algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
 */
public class E_Array_Map_MissingNumber_Offer53 {

    //排序 100% 70%
    public int missingNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) return i;
        }

        return n;
    }

    //Hash
    public int missingNumber_hash(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int n = nums.length + 1;
        for (int i = 0; i < n - 1; i++) {
            set.add(nums[i]);
        }
        int missing = -1;
        for (int i = 0; i <= n - 1; i++) {
            if (!set.contains(i)) {
                missing = i;
                break;
            }
        }
        return missing;
    }

    //Math, sum!! NB
    public int missingNumber_sum(int[] nums) {
        int n = nums.length + 1;
        int total = n * (n - 1) / 2;
        int arrSum = 0;
        for (int i = 0; i < n - 1; i++) {
            arrSum += nums[i];
        }
        return total - arrSum;
    }

}
