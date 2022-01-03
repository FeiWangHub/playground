package com.fei.playground.algorithm.leetcode;

/**
 * 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class M_DP_MaxCommonSubStr {

    /**
     * 评论区的DP解法 但是性能低
     */
    public int longestCommonSubsequence(String text1, String text2) {
        //评论区的DP解法
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j]);
                dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
            }
        }
        return dp[m][n];
    }
}
