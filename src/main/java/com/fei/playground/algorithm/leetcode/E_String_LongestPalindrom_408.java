package com.fei.playground.algorithm.leetcode;

/**
 * 最长回文串
 * 给定一个包含大写字母和小写字母的字符串s，返回通过这些字母构造成的 最长的回文串。
 * 在构造过程中，请注意 区分大小写 。比如"Aa"不能当做一个回文字符串。
 *
 * https://leetcode.cn/problems/longest-palindrome/
 */
public class E_String_LongestPalindrom_408 {

    /**
     * 100% 68%
     * 拿int[]当成 hashmap来用
     */
    public int longestPalindrome(String s) {
        int count[] = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }
        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

}
