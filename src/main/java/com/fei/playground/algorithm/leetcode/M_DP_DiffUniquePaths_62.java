package com.fei.playground.algorithm.leetcode;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 问总共有多少条不同的路径？
 * https://leetcode.cn/problems/unique-paths/
 */
public class M_DP_DiffUniquePaths_62 {

    /**
     * 手撕100% 19% 空间时间都是:m*n
     * 1 定义dp： 一个m*n的二维矩阵，dp[m][n]代表 到达mn有多少种路径
     * 2 转移方程式：dp[m][n] = dp[m][n-1] + dp[m-1][n]
     * 3 初始化：dp[0][0] = 0，横轴[0][*]和纵轴[*][0]都是1
     * 4 遍历顺序：横向，一层一层遍历
     * 5 print
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }
        //Arrays.fill(dp[0], 1);

        //遍历dp
        for (int row = 1; row < dp.length; row++) {
            for (int col = 1; col < dp[0].length; col++) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 数学解法
     */
    public int uniquePaths_math(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

}
