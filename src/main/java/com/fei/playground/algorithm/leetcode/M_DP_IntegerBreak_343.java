package com.fei.playground.algorithm.leetcode;

/**
 * 343. 整数拆分
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 * 返回 你可以获得的最大乘积 。
 * https://leetcode.cn/problems/integer-break/
 */
public class M_DP_IntegerBreak_343 {

    /**
     * 手撕dp 80% 82% 时间n2平方 空间n
     * 1. 定义dp[i]: 代表整数i拆解为k个整数后的最大乘积
     * 2. 转移方程式：dp[i] = max(dp[i], max((i - j) * j, dp[i - j] * j));
     * (那么从1遍历j，比较(i - j) * j和dp[i - j] * j 取最大的。)
     * 3. 初始化: dp[2] = 1
     * 4. 遍历顺序
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                //从2遍历j，比较(i - j) * j和dp[i - j] * j 取最大的
                dp[i] = Math.max(dp[i], j * (i - j));
                dp[i] = Math.max(dp[i], j * dp[i - j]);
            }
        }
        return dp[n];
    }

    /**
     * 数学解法 100% 31% 时间空间都是1
     * 三生万物，尽可能多的拆成3，其次拆成2
     * 1.数字拆分为更多的  3 3，其次为  2 2，最差为  1 1，可让乘积最大化。
     * 2.但是，若最终拆出来，剩下一个  1 1，我们将其中一个  3 + 1 3+1 ，组成一个  4 4，可让乘积更大化。
     */
    public int integerBreak_math(int n) {
        // 方法一：数学方法
        if (n <= 3) {
            return n - 1;
        }
        int quotient = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return (int) Math.pow(3, quotient - 1) * 4;
        } else {
            return (int) Math.pow(3, quotient) * 2;
        }
    }
}
