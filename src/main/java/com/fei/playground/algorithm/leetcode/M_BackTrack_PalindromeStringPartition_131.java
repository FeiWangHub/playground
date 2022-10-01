package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 分割回文串
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * https://leetcode.cn/problems/palindrome-partitioning/
 */
public class M_BackTrack_PalindromeStringPartition_131 {

    static List<List<String>> res = new LinkedList<>();
    static LinkedList<String> curList = new LinkedList<>();

    /**
     * 手写 35.88% 5%
     */
    public static List<List<String>> partition(String s) {
        backtrack(s, 0);
        return res;
    }

    /**
     * @param start 剩余字符串的start idx
     */
    public static void backtrack(String s, int start) {
        if (start > s.length()) return;
        if (start == s.length()) {//finish
            res.add(new LinkedList<>(curList));
            return;
        }

        //剩余的可分割的字符串，尝试从start分割到i
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                curList.add(s.substring(start, i+1));
                backtrack(s, i + 1);
                curList.removeLast();
            }
        }
    }

    public static boolean isPalindrome(String s, int start, int end) {
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
     * 击败98%
     */
    //声明一个全局变量ans
    List<List<String>> ans = new ArrayList<>();

    //主方法
    public List<List<String>> partition_array(String s) {
        backtrack(s.toCharArray(), 0, new ArrayList());
        return ans;
    }

    //回溯方法
    void backtrack(char[] s, int startIndex, List<String> path) {
        //当startIndex越界时返回
        if(startIndex == s.length) {
            //将当前path添加到ans中，注意：需要新造一个ArrayList
            ans.add(new ArrayList(path));
            return;
        }
        //从startIndex开始枚举
        for(int i = startIndex; i < s.length; i++) {
            if(check(s, startIndex, i)) {
                //从startIndex到i如果是回文串，则将此串加入path，并向下递归
                path.add(new String(s, startIndex, i - startIndex + 1));
                backtrack(s, i + 1, path);
                //递归完毕后，记得将此串从path中删除，这是回溯算法的关键步骤
                path.remove(path.size() - 1);
            }
        }
    }

    //辅助方法，判断从start到end是否回文
    boolean check(char[] s, int start, int end) {
        while(start <= end) {
            if(s[start++] != s[end--]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        partition("aab");
        System.out.println(res);
    }
}
