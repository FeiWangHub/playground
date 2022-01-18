package com.fei.playground.algorithm.leetcode;

/**
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * https://leetcode-cn.com/problems/palindromic-substrings/
 */
public class M_String_CountPalindromeSubStr_647 {

    //评论区大神的答案 中心扩张法
    int num = 0;
    public int countSubstrings(String s) {
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
