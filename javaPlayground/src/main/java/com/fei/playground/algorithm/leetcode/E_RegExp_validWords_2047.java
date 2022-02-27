package com.fei.playground.algorithm.leetcode;

import java.util.regex.Pattern;

/**
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 * https://leetcode-cn.com/problems/number-of-valid-words-in-a-sentence/
 */
public class E_RegExp_validWords_2047 {

    /**
     * 13% 5.03%
     */
    public static int countValidWords(String sentence) {
        String[] tokens = sentence.split(" ");
        int count = 0;

        String pattern = "^(([a-z]*)|([a-z]+-[a-z]+))[!.,]?$";

        for(String t:tokens){
            if(t.length()==0) continue;
            if(Pattern.matches(pattern, t)) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countValidWords("i am super-hero! DD"));
    }

//    public boolean isValid(String word){
//        //lower case
//        //at most one - between words
//        //at most one sentense
//
//
//    }

}
