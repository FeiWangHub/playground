package com.fei.playground.algorithm.leetcode;

/**
 * TODO 可以用这个题练习DP
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class M_String_LongestPalindrome_5 {

    //我改造的中央扩张法
    String cur = "";
    public String longestPalindrome(String s) {
        for (int i=0; i < s.length(); i++){
            count(s, i, i);//回文串长度为奇数
            count(s, i, i+1);//回文串长度为偶数
        }
        return cur;
    }

    public void count(String s, int start, int end){
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)){
            if(cur.length() < end-start+1) cur = s.substring(start, end+1);
            start--;
            end++;
        }
    }

    //暴力破解法 字节面试使用
    static String result = "";
    public static String solution(String s){
        for(int i=0; i<s.length(); i++){
            for(int j=i; j<s.length(); j++){
                if(isValid(s, i, j) && (j-i+1)>result.length()){
                    result = s.substring(i, j+1);
                }
            }
        }
        return result;
    }

    //start end inclusive
    public static boolean isValid(String s, int start, int end){
        while(start < end){
            if(s.charAt(start)==s.charAt(end)){
                start++;
                end--;
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
}
