package com.fei.playground.algorithm.leetcode;

/**
 * 300. 最长递增子序列
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class M_DP_LongestASCSubsequence_300 {

    /**
     * 手撕DP 47% 94% 时间o(n2) 空间o(n)
     * dp[i]代表截止到i为止，最长递增子序列长度
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;

        int max = 1;
        int[] dp = new int[nums.length];//dp[i]代表截止到i为止，最长递增子序列长度
        dp[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            //计算dp[i],从i开始向前搜索，找到第一个比i小的数字j，dp[i]=dp[j]+1
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    max = Math.max(max, dp[i]);
                }

            }
        }
        //System.out.println(Arrays.toString(dp));
        return max;
    }

    /**
     * 官方贪心二分法 时间nLogn 空间n 99% 35% TODO 需要学习
     */
    public int lengthOfLIS_byGreedyAndBSearch(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
