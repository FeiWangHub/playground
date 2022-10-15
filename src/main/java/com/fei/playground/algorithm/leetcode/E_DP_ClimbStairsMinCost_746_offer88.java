package com.fei.playground.algorithm.leetcode;

/**
 * 746. 使用最小花费爬楼梯
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
 * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 * 请你计算并返回达到楼梯顶部的最低花费。
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 */
public class E_DP_ClimbStairsMinCost_746_offer88 {

    /**
     * 手撕DP 100% 32% 时间空间都是N
     * 1 定义dp数组和dp[i]含义: 爬到i层所需要的最小cost
     * 2 递推公式: dp[i] = Math.min(dp[i-2]+cost[i-2],dp[i-1]+cost[i-1])
     * 3 初始化dp数组 len+1  dp[]={0,0,0}
     * 4 遍历方向
     * 5 打印dp
     */
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length <= 1) return 0;
        int[] dp = new int[cost.length + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[dp.length - 1];
    }

}
