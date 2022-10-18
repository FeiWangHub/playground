package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 90. 子集 II (带重复元素)
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * https://leetcode.cn/problems/subsets-ii/
 */
public class M_Backtrack_Subset2_withDup_90 {

    HashSet<String> set = new HashSet<>();
    LinkedList<Integer> curList = new LinkedList<>();
    List<List<Integer>> res = new LinkedList<>();
    boolean[] used;

    /**
     * 教程第三版 boolean去重法 99% 87%
     * TODO 需要学习
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0) {
            res.add(curList);
            return res;
        }
        used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(0, nums);
        return res;
    }

    //计算包含idx的 所有宽度的子集组合
    public void backtrack(int curIdx, int[] nums) {
        res.add(new LinkedList<>(curList));
        if (curIdx >= nums.length) return;

        for (int i = curIdx; i < nums.length; i++) {
            //vis[i - 1]代表同一树枝使用过， !vis[i - 1]为同一树层使用过
            if ((i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue;
            curList.add(nums[i]);
            used[i] = true;
            backtrack(i + 1, nums);
            curList.removeLast();
            used[i] = false;
        }
    }

    /**
     * 手撕第二版 set去重 6.2% 60%
     */
    public List<List<Integer>> subsetsWithDup_mine2(int[] nums) {
        Arrays.sort(nums);
        backtrack_mine2(0, nums);
        return res;
    }

    //计算包含idx的 所有宽度的子集组合
    public void backtrack_mine2(int curIdx, int[] nums) {
        if (curIdx == nums.length) {
            if (!set.add(linklistStr(curList))) return;//去重
            res.add(new LinkedList<>(curList));
            return;
        }

        curList.add(nums[curIdx]);
        backtrack_mine2(curIdx + 1, nums);
        curList.removeLast();
        backtrack_mine2(curIdx + 1, nums);
    }

    /**
     * 手撕第一版 set去重 6.2% 5.12%
     */
    public List<List<Integer>> subsetsWithDup_mine1(int[] nums) {
        Arrays.sort(nums);
        for (int len = 0; len <= nums.length; len++) {//取不同宽度的set
            backtrack_mine1(0, len, nums);
        }
        return res;
    }

    //计算包含idx的 所有宽度的子集组合
    public void backtrack_mine1(int curIdx, int targetLen, int[] nums) {
        if (curList.size() == targetLen) {
            if (!set.add(linklistStr(curList))) return;//去重
            res.add(new LinkedList<>(curList));
        }

        for (int i = curIdx; i < nums.length; i++) {
            curList.add(nums[i]);
            backtrack_mine1(i + 1, targetLen, nums);
            curList.removeLast();
        }
    }

    public String linklistStr(LinkedList<Integer> input) {
        return new ArrayList<>(input).toString();
    }

    public static void main(String[] args) {
        M_Backtrack_Subset2_withDup_90 t = new M_Backtrack_Subset2_withDup_90();
        List<List<Integer>> res = t.subsetsWithDup_mine2(new int[]{1, 2, 2});
        res.forEach(l -> System.out.println(l));
    }

}
