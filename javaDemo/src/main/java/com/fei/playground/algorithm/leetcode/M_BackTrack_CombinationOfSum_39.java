package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
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

}
