package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/subsets/
 * 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class M_BackTrack_Subset_78 {

    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> curList = new LinkedList<>();

    /**
     * 手写回溯 25% 7.4%
     * 全排列 目测复杂度O(N!)
     */
    public List<List<Integer>> subsets(int[] nums) {
        result.add(new LinkedList<>());
        for(int i=1; i<=nums.length; i++){
            backtrack(nums, 0, i);
        }
        return result;
    }

    public void backtrack(int[] nums, int curIdx, int targetLen){
        if(curList.size() == targetLen){
            result.add(new LinkedList<>(curList));
            return;
        }

        for(int i=curIdx; i<nums.length; i++){
            curList.add(nums[i]);
            backtrack(nums, i+1, targetLen);
            curList.removeLast();
        }
    }

    /**
     * 官方2 递归 回溯 官方 100% 67%
     */
    public List<List<Integer>> subsets_backtrack_best(int[] nums) {
        dfs(0, nums);
        return result;
    }

    public void dfs(int cur, int[] nums) {
        if (cur == nums.length) {
            result.add(new ArrayList<Integer>(curList));
            return;
        }
        curList.add(nums[cur]);
        dfs(cur + 1, nums);
        curList.remove(curList.size() - 1);
        dfs(cur + 1, nums);
    }

    /**
     * 官方1 迭代法实现子集枚举
     * 记原序列中元素的总数为 nn。原序列中的每个数字 a_ia
     *   的状态可能有两种，即「在子集中」和「不在子集中」。我们用 11 表示「在子集中」，00 表示不在子集中，
     *   那么每一个子集可以对应一个长度为 nn 的 0/10/1 序列，第 ii 位表示 a_ia
     *   是否在子集中。例如，n = 3n=3 ，a = \{ 5, 2, 9 \}a={5,2,9} 时：
     *
     * 可以发现 0/10/1 序列对应的二进制数正好从0到2^n - 1个
     */
    public List<List<Integer>> subsets_official_by_binary(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            curList.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    curList.add(nums[i]);
                }
            }
            result.add(new ArrayList<Integer>(curList));
        }
        return result;
    }

}
