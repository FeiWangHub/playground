package com.fei.playground.algorithm.leetcode;

/**
 * https://leetcode-cn.com/problems/wildcard-matching/
 * 此题类似Amazon的DEMO中的Coupon Code题
 */
public class H_DP_RegExpMatch44 {

    /**
     * 网友思路
     * 1.状态定义 dp[i][j] 表示 p 的前 i 个字符和 s 的前 j 个字符是否匹配。
     * 2.状态转移 : 如果 p[i - 1] == s[j - 1] 或 p[i - 1] == '?'，表示当前的字符串是匹配的，dp[i][j] 可以从 dp[i - 1][j - 1] 转移而来。
     * 3.初始条件 : dp[0][0] = true 表示空串是匹配的。
     * 作者：sweetiee
     *     链接：https://leetcode-cn.com/problems/wildcard-matching/solution/zi-fu-chuan-dong-tai-gui-hua-bi-xu-miao-dong-by-sw/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isMatch(String s, String p) {
        int len1 = p.length(), len2 = s.length();
        boolean[][] dp = new boolean[len1 + 1] [len2 + 1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {//*位置一定相等
            if (p.charAt(i - 1) != '*') {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '?') { //如果当前匹配，去看次此字符串的上一个字符位，是否匹配
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(i - 1) == '*') { //如果当前是星号，去看
                    dp[i][j] = dp[i - 1][j] | dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }
}
