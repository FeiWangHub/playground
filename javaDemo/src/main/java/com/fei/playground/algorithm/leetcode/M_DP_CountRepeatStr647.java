package com.fei.playground.algorithm.leetcode;

/**
 * 找出回文的数量 TODO这个需要自己写一下
 * https://leetcode-cn.com/problems/palindromic-substrings/comments/
 */
public class M_DP_CountRepeatStr647 {

    /**
     * 评论 二位DP解法
     */
    public int countSubstrings_DP(String s) {
        int len, ans = 0;
        if (s == null || (len = s.length()) < 1) return 0;
        //dp[i][j]：s字符串下标i到下标j的字串是否是一个回文串，即s[i, j]
        boolean[][] dp = new boolean[len][len];
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                //当两端字母一样时，才可以两端收缩进一步判断
                if (s.charAt(i) == s.charAt(j)) {
                    //i++，j--，即两端收缩之后i,j指针指向同一个字符或者i超过j了,必然是一个回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        //否则通过收缩之后的字串判断
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {//两端字符不一样，不是回文串
                    dp[i][j] = false;
                }
            }
        }
        //遍历每一个字串，统计回文串个数
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dp[i][j]) ans++;
            }
        }
        return ans;
    }

    /**
     * 评论递归解法
     */
    int num = 0;
    public int countSubstrings_recursive(String s) {
        for (int i=0; i < s.length(); i++){
            count(s, i, i);//回文串长度为奇数
            count(s, i, i+1);//回文串长度为偶数
        }
        return num;
    }

    public void count(String s, int start, int end){
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            num++;
            start--;
            end++;
        }
    }

}
