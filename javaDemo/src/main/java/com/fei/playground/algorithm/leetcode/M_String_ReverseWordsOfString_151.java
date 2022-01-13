package com.fei.playground.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 */
public class M_String_ReverseWordsOfString_151 {

    /**
     * 方法一：双指针 击败28 69%
     * 倒序遍历字符串
     * 记录单词左右索引边界
     * 每确定一个单词的边界，则将其添加至单词列表
     * 最终，将单词列表拼接为字符串，并返回即可。
     */
    public String reverseWords(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
            while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }

    /**
     * 第一版 击败20 20%
     */
    public String reverseWords_1st(String s) {
        Stack<String> result = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char cur = ' ';

        for(int i=0; i<s.length(); i++){
            cur = s.charAt(i);
            if(cur==' ' && sb.length()==0){//空格 上一个也是空格
                continue;
            }else if(cur==' '){//空格 上一个不是空格 添加新字符串
                result.push(sb.toString());
                sb.delete(0,sb.length());
            }else{//字符
                sb.append(cur);
            }
        }

        if(sb.length()!=0) result.add(sb.toString());//最后一个

        sb.delete(0,sb.length());
        while (result.size()!=0){
            sb.append(result.pop()).append(" ");
        }

        if(sb.length()>0) sb.delete(sb.length()-1, sb.length());

        return sb.toString();
    }

    /**
     * 这个写错了，审题不清，写成了翻转整个字符串
     */
    public String reverseWords_wrong(String s) {

        StringBuilder sb = new StringBuilder();
        char lastVisited = ' ';
        char cur = ' ';

        for(int i=s.length()-1; i>=0; i--){
            if(i==s.length()-1){
                lastVisited = s.charAt(i);
            }

            cur = s.charAt(i);
            if(cur==' ' && lastVisited == ' '){//空格 上一个也是空格
                continue;
            }else{//空格 上一个不是空格
                sb.append(cur);
                lastVisited=cur;
            }
        }

        String result = sb.toString();
        return result.trim();
    }

    public static void main(String[] args) {
        String[] strs = "a   b".split(" ");

        System.out.println(Arrays.toString(strs));
    }

}
