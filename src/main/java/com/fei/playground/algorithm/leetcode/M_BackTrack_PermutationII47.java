package com.fei.playground.algorithm.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 所有数字的排列 input数字可能重复 output组合不能重复
 * 思路：所谓output组合不能重复，其实就是每个数字位置上，每个独立数字都只能出现一次
 *
 * https://leetcode-cn.com/problems/permutations-ii/
 */
public class M_BackTrack_PermutationII47 {

    boolean[] vis;

    /**
     * 官方算法 用boolean[]标记某位置是否已经用过，或者这个相同的数字是否已经用过
     */
    public List<List<Integer>> permuteUnique_offical(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 根据大神的 击败85% 85%的解法改变的解法，击败40 40%
     */
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
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

        HashSet<Integer> visited = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            if (used[i]){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            if (visited.add(nums[i])){
                permuteHelper(nums);
            }
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
//        M_BackTrack_PermutationII47 p = new M_BackTrack_PermutationII47();
//        p.permute(new int[]{1,2,3});
    }

}
