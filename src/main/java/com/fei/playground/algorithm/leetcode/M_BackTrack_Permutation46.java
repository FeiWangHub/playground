package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 所有数字的排列
 *
 * https://leetcode-cn.com/problems/permutations/
 */
public class M_BackTrack_Permutation46 {

    /**
     * boolean[标记法] 100% 87.97%
     */
    boolean[] vis;
    public List<List<Integer>> permute_official(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i]) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

    /**
     * 我的复杂度为n!的递归解法
     */
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        //新版swap递归 击败5% 85%
        dfsNew(nums, 0);

        //旧版list递归 击败5% 5%
        //List<Integer> candidates = Arrays.stream(nums).boxed().collect(Collectors.toList());
        //dfs(candidates, new ArrayList<>(nums.length));
        return result;
    }

    /**
     * swap节省内存空间
     */
    public void dfsNew(int[] nums, int curIdx){
        //出口
        if(curIdx >= nums.length){
            List<Integer> l = Arrays.stream(nums).boxed().collect(Collectors.toList());
            result.add(l);
            return;
        }

        for(int i=curIdx; i<nums.length; i++){
            //当前状态
            swap(nums, curIdx, i);
            //下一状态
            dfsNew(nums, curIdx+1);
            //回到当前状态
            swap(nums, curIdx, i);
        }
    }

    public void swap(int[] nums, int idx1, int idx2){
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public void dfs(List<Integer> candidates, ArrayList<Integer> path){
        //出口
        if(candidates.size() == 1){
            path.add(candidates.get(0));
            result.add(new ArrayList<>(path));
            return;
        };

        //当前状态 就是path
        ArrayList<Integer> hisPath = new ArrayList<>(path);
        ArrayList<Integer> hisCandidates = new ArrayList<>(candidates);
        int len = candidates.size();
        for(int i=0; i<len; i++){
            System.out.println("i: "+ i +", candidates:"+ candidates.toString());

            //运行下一状态
            path.add(candidates.get(i));
            candidates.remove(candidates.get(i));
            dfs(candidates, path);

            //回到当前状态
            path = new ArrayList<>(hisPath);
            candidates = new ArrayList<>(hisCandidates);
        }
    }

    /**
     * 大神的 击败85% 85%的解法
     */
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] used;
    public List<List<Integer>> permuteBest(int[] nums) {
        if (nums.length == 0){
            return result;
        }
        used = new boolean[nums.length];
        permuteHelper(nums);
        return result;
    }

    private void permuteHelper(int[] nums){
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (used[i]){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            permuteHelper(nums);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        M_BackTrack_Permutation46 p = new M_BackTrack_Permutation46();
        p.permute(new int[]{1,2,3});
    }

}
