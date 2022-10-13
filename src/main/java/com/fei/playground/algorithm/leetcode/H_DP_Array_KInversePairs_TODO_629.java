package com.fei.playground.algorithm.leetcode;

/**
 * 629. K个逆序对数组
 * 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * 由于答案可能很大，只需要返回 答案 mod 109 + 7 的值。
 * https://leetcode.cn/problems/k-inverse-pairs-array/
 */
public class H_DP_Array_KInversePairs_TODO_629 {

    /**
     * 官方 DP 92% 86%
     * TODO 有待理解
     * https://leetcode.cn/problems/k-inverse-pairs-array/solution/kge-ni-xu-dui-shu-zu-by-leetcode-solutio-0hkr/
     */
    public int kInversePairs(int n, int k) {
        final int MOD = 1000000007;
        int[][] f = new int[2][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k; ++j) {
                int cur = i & 1, prev = cur ^ 1;
                f[cur][j] = (j - 1 >= 0 ? f[cur][j - 1] : 0) - (j - i >= 0 ? f[prev][j - i] : 0) + f[prev][j];
                if (f[cur][j] >= MOD) {
                    f[cur][j] -= MOD;
                } else if (f[cur][j] < 0) {
                    f[cur][j] += MOD;
                }
            }
        }
        return f[n & 1][k];
    }

    /**
     * 暴力递归？待理解
     */
//    int MOD = 1000000007;
//    public int kInversePairs(int n, int k) {
//        return (int) (dfs(n, k) % MOD);
//    }
//
//    private long dfs(int n, int k) {
//        if (k > n * (n - 1) / 2) {
//            return 0;
//        }
//        if (n == 1) {
//            return k == 0 ? 1 : 0;
//        }
//
//        long ans = 0;
//        // 比如，求解 f(3)(3)，求解 f(2)(0) 是没有意义的，因为两个数的数组没有倒数第三位
//        for (int i = Math.max(0, k - n + 1); i <= k; i++) {
//            ans += dfs(n - 1, i);
//        }
//
//        return ans % MOD;
//    }
}
