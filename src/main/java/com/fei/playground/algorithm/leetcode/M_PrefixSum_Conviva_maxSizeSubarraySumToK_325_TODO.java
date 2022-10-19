package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 325. 和等于 k 的最长子数组长度
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。如果不存在任意一个符合要求的子数组，则返回 0。
 * https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k/
 */
public class M_PrefixSum_Conviva_maxSizeSubarraySumToK_325_TODO {

    /**
     * 使用前缀和 preSum 来记录截止到 i 位置时，得到的 nums[0:i]之和
     * 使用哈希表来记录第一次 preSum 出现的位置 i
     * 需要注意的是，对于前缀和 0 ，其出现的位置在数组开始前，也就是 -1 TODO
     * https://leetcode.cn/problems/maximum-size-subarray-sum-equals-k/solution/java-qian-zhui-he-fang-fa-zhu-xing-zhu-s-p5jb/
     */
    public int maxSubArrayLen_comment(int[] nums, int k) {
        int n = nums.length;
        // 哈希表，映射前缀和值到第一次出现的下标位置
        Map<Integer, Integer> preSumIndex = new HashMap<>();
        int ans = 0;
        // 前缀和
        int preSum = 0;
        // 0 出现的位置在 -1 位置处
        preSumIndex.put(0, -1);
        for (int i = 0; i < n; ++i) {
            // 累加前缀和
            preSum += nums[i];
            // 确保记录的是第一次出现的位置
            if (!preSumIndex.containsKey(preSum)) {
                preSumIndex.put(preSum, i);
            }
            // 检查一下是否需要更新答案
            if (preSumIndex.containsKey(preSum - k)) {
                ans = Math.max(ans, i - preSumIndex.get(preSum - k));
            }
        }
        return ans;
    }

    /**
     * 手撕前缀和ing 有bug
     */
    public static int maxSubArrayLen(int[] nums, int k) {
        //前缀和 sums[i]代表位置i之前所有数字的和（包括i）
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        System.out.println("前缀和为" + Arrays.toString(sums));

        HashMap<Integer, Integer> sum2minIdxMap = new HashMap<>();
        sum2minIdxMap.put(0, 0);
        int resStartIdx = 0;
        int resEndIdx = 0;

        for (int i = 1; i < sums.length; i++) {
            int curSum = sums[i];
            int target = curSum - k;

            if (sum2minIdxMap.containsKey(target)) {//此区间合法
                int start = sum2minIdxMap.get(target) + 1;//nums的idx
                int end = i;//nums的idx,inclusive
                if (end - start > resEndIdx - resStartIdx) {
                    resEndIdx = end;
                    resStartIdx = start;
                    System.out.println("当前合法的区间为start: " + resStartIdx + " end:" + resEndIdx);
                }
            }

            if (!sum2minIdxMap.containsKey(curSum)) {
                sum2minIdxMap.put(curSum, i);
            }
        }

        return resEndIdx - resStartIdx + 1;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
    }

}
