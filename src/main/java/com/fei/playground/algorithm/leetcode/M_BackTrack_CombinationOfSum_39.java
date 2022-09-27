package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个无重复元素 的整数数组candidates 和一个目标整数target，
 * 找出candidates中可以使数字和为目标数target的所有不同组合，并以列表形式返回。
 *
 * 你可以按任意顺序返回这些组合。
 * candidates中的同一个数字可以无限制重复被选取。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 * https://leetcode-cn.com/problems/combination-sum/
 */
public class M_BackTrack_CombinationOfSum_39 {
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> curPath = new LinkedList<>();
    int[] candidates;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        dfs(target, 0);

        return result;
    }

    /**
     * 寻找组成target的所有可能性
     * @param target 本轮的target
     * @param canIdx 由于不能重复组合，所以，candidates从小到大排列，从小加到大，当前元素可以重复使用；之前使用过的较小的元素就不重复使用了
     */
    public void dfs(int target, int canIdx) {
        //出口
        if (target == 0) {
            result.add(new ArrayList<>(curPath));
            return;
        }

        for (int i=canIdx; i < candidates.length; i++) {
            int can = candidates[i];
            if (can > target) break;
            int combine = target - can;

            curPath.add(can);//记录当前状态 curPath
            dfs(combine,i);//递归
            curPath.removeLast();//回到当前状态 curPath
        }
    }

    /**
     * 95% 5% 手撸
     * 2022.9
     */
    public List<List<Integer>> combinationSum_mine(int[] candidates, int target) {
        dfs(candidates, 0, target);
        return result;
    }

    public void dfs(int[] candidates, int curIdx ,int curTarget){
        if(curIdx >= candidates.length) return;
        if(curTarget < 0) return;
        if(curTarget == 0){
            result.add(new LinkedList<>(curPath));
            return;
        }

        curPath.add(candidates[curIdx]);
        dfs(candidates, curIdx, curTarget - candidates[curIdx]);
        curPath.removeLast();
        dfs(candidates, curIdx+1, curTarget);
    }

}
