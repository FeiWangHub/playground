package com.fei.playground.algorithm.leetcode;

/**
 * TODO 可以用这个题练习DP
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class M_String_DP_TODO_LongestPalindrome_5 {

    /**
     * 我改造的中央扩张法 81% 55%; 时间n2，空间o(1)
     */
    String cur = "";

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            count(s, i, i);//回文串长度为奇数
            count(s, i, i + 1);//回文串长度为偶数
        }
        return cur;
    }

    public void count(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            if (cur.length() < end - start + 1) cur = s.substring(start, end + 1);
            start--;
            end++;
        }
    }

    //暴力破解法 字节面试使用
    static String result = "";

    public static String solution(String s) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isValid(s, i, j) && (j - i + 1) > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

    //start end inclusive
    public static boolean isValid(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 官方动态规划 需要学习 TODO 45% 31% 时间n2，空间n2
     * 只有s[i+1:j−1]是回文串，并且 s 的第 i 和 j 个字母相同时，s[i:j] 才会是回文串
     */
    public String longestPalindrome_byDP(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

}
