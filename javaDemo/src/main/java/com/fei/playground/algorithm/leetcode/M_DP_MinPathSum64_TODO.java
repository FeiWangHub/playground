package com.fei.playground.algorithm.leetcode;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * https://leetcode-cn.com/problems/minimum-path-sum/
 * 解题思路 在每个位置上记录上它的处境的最小值 https://www.ixigua.com/6932297206066053644?logTag=7eecfb84fe44f38e97ca
 */
public class M_DP_MinPathSum64_TODO {

    public int minPathSum(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;

        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < c; i++) {//第一列
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < r; i++) {//第一行
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        //第i行到第r-1行，第j列到第j-1列，每个位置的最小路径
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[r - 1][c - 1];
    }

}
