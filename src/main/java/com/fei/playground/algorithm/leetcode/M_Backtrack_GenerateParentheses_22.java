package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class M_Backtrack_GenerateParentheses_22 {

    List<String> res = new LinkedList<>();

    /**
     * 官方backtrack 100% 25%
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 递归 76% 21%
     */
    public List<String> generateParenthesis_recursion(int n) {
        //回溯？stack，每放入一个)之后，都有n种选择
        dfs(n, 0, "");
        return res;
    }

    public void dfs(int left, int right, String str) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }

        if (left > 0) {
            dfs(left - 1, right + 1, str + "(");
        }

        if (right > 0) {
            dfs(left, right - 1, str + ")");
        }
    }

}
