package com.fei.playground.algorithm.leetcode;

import java.util.HashMap;

/**
 * 和为 K 的子数组
 * https://leetcode.cn/problems/subarray-sum-equals-k/
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的连续子数组的个数 。
 */
public class M_PrefixSum_Map_SubArray_To_K_560 {

    /**
     * 31.64% 49.63%
     * 暴力破解
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int curSum = 0;
        int cur = 0;

        for (int i = 0; i < nums.length; i++) {
            cur = nums[i];
            curSum = cur;
            if (cur == k) count++;

            for (int j = i + 1; j < nums.length; j++) {
                curSum += nums[j];
                if (curSum == k) count++;
            }
        }

        return count;
    }

    //前缀和算法
    public int subarraySumAnswer(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len + 1];

        for (int i = 1; i <= len; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        HashMap<Integer, Integer> sum2countMap = new HashMap<>();
        sum2countMap.put(0, 1);
        int count = 0;
        for (int i = 1; i < sums.length; i++) {
            int cur = sums[i];
            int target = cur - k;
            count += sum2countMap.getOrDefault(target, 0);
            sum2countMap.put(cur, sum2countMap.getOrDefault(cur, 0) + 1);
        }

        return count;
    }

}
