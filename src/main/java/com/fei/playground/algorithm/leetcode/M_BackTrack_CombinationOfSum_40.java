package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和 II
 * 给定一个候选人编号的集合candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次。
 * 注意：解集不能包含重复的组合。
 * <p>
 * https://leetcode.cn/problems/combination-sum-ii/
 */
public class M_BackTrack_CombinationOfSum_40 {

    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> curPath = new LinkedList<>();

    /**
     * 第一版手撕 43% 5%
     * 应该还有改进空间
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, 0, target);
        return result;
    }

    public void dfs(int[] candidates, int curIdx, int curTarget) {
        if (curTarget == 0) {
            result.add(new LinkedList<>(curPath));
            return;
        }
        if (curIdx >= candidates.length) return;
        if (curTarget < 0) return;

        curPath.add(candidates[curIdx]);
        dfs(candidates, curIdx + 1, curTarget - candidates[curIdx]);
        curPath.removeLast();

        curIdx++;
        while (curIdx < candidates.length && candidates[curIdx] == candidates[curIdx - 1]) {
            curIdx++;
        }
        dfs(candidates, curIdx, curTarget);
    }

}
