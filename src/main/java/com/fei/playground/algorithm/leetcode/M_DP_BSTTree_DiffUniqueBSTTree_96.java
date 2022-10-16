package com.fei.playground.algorithm.leetcode;

/**
 * 96. 不同的二叉搜索树
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 * 1 <= n <= 19
 * https://leetcode.cn/problems/unique-binary-search-trees/
 */
public class M_DP_BSTTree_DiffUniqueBSTTree_96 {

    /**
     * DP 100% 92% TODO 这个还没完全理解方程式
     * 1. dp[i]: n个节点所能构成的最多种的二叉树
     * 2. dp方程式：递推公式:dp[i] += dp[j - 1] * dp[i - j]; ，j-1 为j为头结点左子树节点数量，i-j 为以j为 头结点右子树节点数量
     * n      0  1  2  3  4   5   6
     * count  0  1  2  5  14  42  132
     * 3. 初始化 dp[0] = 0
     * ---------------
     * FEI思维风暴：(这个风暴是错的)
     * 1.每次多一个n，就是多一个新的顶点，并且旧数字都在新顶点左侧，总可能性是dp[n-1]
     * 2.每次多一个n，n前边的顶点们，右子树都会多一个选项n:
     * 对于顶点n-1,新增0可能性(右子树数量0变成1，dp[0])；右子树可能性是dp[1](没变)，增量 0
     * 对于顶点n-2,新增2可能性(右子树数量1变成2，dp[2]种可能性);右子树可能性dp[2], 增量 dp[2]-dp[1]
     * 对于顶点n-3,新增x可能性(右子树数量2变成3，dp[3]种可能性);右子树可能性dp[3], 增量 dp[3]-dp[2]
     * ....
     * 对于顶点1，右子树可能性dp[n-1]，增量: dp[n-1] - dp[n-2]; 总量 dp[1]+(dp[n-1] - dp[n-2])
     * 对于顶点2, 右子树可能性dp[n-2]，增量：dp[2] - dp[1]；总量 dp[2]+(dp[n-1] - dp[n-2])
     * ....dp[j]+(dp[n-j] - dp[n-j-1])
     * 对于顶点n-1,右子树可能性dp[n-n-1]
     */
    public int numTrees(int n) {
        if (n < 2) return n;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //递推公式:dp[i] += dp[j - 1] * dp[i - j]; ，j-1 为j为头结点左子树节点数量，i-j 为以j为头结点右子树节点数量
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 手撕backtrack法 5% 57%
     * 让每一个数组轮流做定点 计算可能性
     */
    public int numTrees_backtrack(int n) {
        return backtrack(1, n);
    }

    public int backtrack(int start, int end) {
        if (start >= end) {
            return 1;
        }

        int sum = 0;
        //每一个点轮流当root，计算可能性
        for (int i = start; i <= end; i++) {
            int left = backtrack(start, i - 1);
            int right = backtrack(i + 1, end);
            sum += (left * right);
        }
        return sum;
    }

}
