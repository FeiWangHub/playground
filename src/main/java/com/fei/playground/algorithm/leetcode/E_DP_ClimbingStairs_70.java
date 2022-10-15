package com.fei.playground.algorithm.leetcode;

/**
 * 爬楼梯
 * https://leetcode.cn/problems/climbing-stairs/submissions/
 */
public class E_DP_ClimbingStairs_70 {

    /**
     * 100% 85%
     * 空间优化版本
     */
    public int climbStairs(int n) {
        if (n < 3) return n;
        int[] dp = new int[]{0, 1, 2};
        int sum;
        for (int i = 3; i < n + 1; i++) {
            sum = dp[1] + dp[2];
            dp[1] = dp[2];
            dp[2] = sum;
        }
        return dp[2];
    }

    /**
     * 试水 手撕 100% 18%
     * 首先是dp[i - 1]，上i-1层楼梯，有dp[i - 1]种方法，那么再一步跳一个台阶不就是dp[i]了么。
     * 还有就是dp[i - 2]，上i-2层楼梯，有dp[i - 2]种方法，那么再一步跳两个台阶不就是dp[i]了 么。
     * 那么dp[i]就是 dp[i - 1]与dp[i - 2]之和!
     * 所以dp[i] = dp[i - 1] + dp[i - 2] 。
     */
    public int climbStairs_main(int n) {
        if (n < 3) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        //System.out.println(dpays.toString(dp));
        return dp[n];
    }

    /**
     * 数学解法
     * https://leetcode.cn/problems/climbing-stairs/solution/hua-jie-suan-fa-70-pa-lou-ti-by-guanpengchn/
     */
    public int climbStairs_math(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib_n = Math.pow((1 + sqrt_5) / 2, n + 1) - Math.pow((1 - sqrt_5) / 2, n + 1);
        return (int) (fib_n / sqrt_5);
    }

    /**
     * 一次可以爬1..2..3..step个台阶
     * 总共n个台阶
     */
    public int climbStairs_Nstep(int n, int step) {
        int[] dp = new int[n + 1];
        dp[0] = 1;//hmmm 这里也可以不是1
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= step; j++) {
                if (i - j >= 0) dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }

}
