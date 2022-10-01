package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * https://leetcode.cn/problems/combinations/
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 */
public class M_BackTrack_Combination_77 {

    static List<List<Integer>> results;
    static LinkedList<Integer> curList;
    static int targetLen;

    /**
     * 我的第一个解法
     * 带减枝 70% 9.59%
     */
    public static List<List<Integer>> combine(int n, int k) {
        results = new LinkedList<>();
        curList = new LinkedList<>();
        targetLen = k;
        backtrack(n, 1);
        return results;
    }

    public static void backtrack(int n, int curIdx) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (curList.size() + (n - curIdx + 1) < targetLen) {
            return;
        }

        //end
        if (curList.size() == targetLen) {
            results.add(new LinkedList<>(curList));
            return;
        }

        for (int i = curIdx; i <= n; i++) {
            curList.addLast(i);
            backtrack(n, i + 1);
            curList.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(1,1));
        System.out.println(combine(4, 2));

//        System.out.println(combine2(1,1));
//        System.out.println(combine2(4,2));
    }

    /**
     * 精选答案
     */
    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private static void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        for (int i = begin; i <= n; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }

}
