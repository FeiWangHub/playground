package com.fei.playground.algorithm.leetcode;

/**
 * TODO 这题我还没自己完成过
 * 用1代表A,2代表B，26个数字代表字母；尝试给出所有字母组合的可能性
 * 最开始我尝试使用回溯，失败
 * 用DP，dp[i]表示前i个字符的解码数
 * https://leetcode-cn.com/problems/decode-ways/
 */
public class M_DP_DecodeWays_91TODO {

    /**
     * 思路更清晰的动态规划
     */
    public int numDecodings_clear(String s) {
        int len = s.length();
        if (len == 0) {
            return 0;
        }

        // dp[i] 以 s[i] 结尾的前缀子串有多少种解码方法
        // dp[i] = dp[i - 1] * 1 if s[i] != '0'
        // dp[i] += dp[i - 2] * 1 if  10 <= int(s[i - 1..i]) <= 26
        int[] dp = new int[len];

        char[] charArray = s.toCharArray();
        if (charArray[0] == '0') {
            return 0;
        }
        dp[0] = 1;

        for (int i = 1; i < len; i++) {
            if (charArray[i] != '0') {
                dp[i] = dp[i - 1];
            }

            int num = 10 * (charArray[i - 1] - '0') + (charArray[i] - '0');
            if (num >= 10 && num <= 26) {
                if (i == 1) {
                    dp[i]++;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[len - 1];
    }

    /**
     * 动态规划解法1
     * 1 我们可以先求前i-1个字符的解码数，但前提条件是当前字符也可以解码（一个字符的话，只要不是0，都可以）
     * 2 还可以求前i-2个字符的解码数，但前提条件是当前字符和前一个字符构成的两个数字是有效的。
     * 看到这里大家应该已经明白了，如果没有条件限制的话，这题解法和爬楼梯完全一样，递归公式其实就是个斐波那契数列
     * dp[i]=dp[i-1]+dp[i-2]
     */
    public int numDecodings(String s) {
        int length = s.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        for (int i = 1; i <= length; i++) {
            //判断截取一个是否符合（只要不是0，都符合）
            if (s.charAt(i - 1) != '0')
                dp[i] = dp[i - 1];
            //判断截取两个是否符合
            if (i >= 2 && (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))
                dp[i] += dp[i - 2];
        }
        return dp[length];
    }

    public static void main(String[] args) {
        M_DP_DecodeWays_91TODO t = new M_DP_DecodeWays_91TODO();
        System.out.println(t.numDecodings_clear("123"));
    }

}
