package com.fei.playground.algorithm.leetcode;

import java.util.HashMap;

/**
 * 两数之和 Two Sum
 * 给定一个整数数组 num和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * https://leetcode.cn/problems/two-sum/
 */
public class E_Sum_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        //注意留意两个数值相等的情况
        HashMap<Integer, Integer> value2idxMap = new HashMap<>(nums.length);
        int idx = 0;
        for (int i : nums) {
            int remaining = target - i;
            if (value2idxMap.containsKey(remaining)) {
                return new int[]{idx, value2idxMap.get(remaining)};
            } else {
                value2idxMap.put(i, idx);
            }
            idx++;
        }
        return null;
    }

}
