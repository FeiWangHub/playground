package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素
 * 给你一个整数数组 nums 。如果任一值在数组中出现 至少两次 ，返回 true;
 * 如果数组中每个元素互不相同，返回 false 。
 */
public class E_Map_ContainsDuplicates_217 {

    //一行stream解法
    public boolean containsDuplicate(int[] nums) {
        return Arrays.stream(nums).distinct().count() != nums.length;
    }

    //排序解法
    public boolean containsDuplicate_bySort(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }

    //哈希解法
    public boolean containsDuplicate_byHash(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int x : nums) {
            if (!set.add(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer 03. 数组中重复的数字
     * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
     * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
     * <p>
     * 原地交换法 每个数字，和每个数字index，是一一对应的 100% 84%
     */
    public int findRepeatNum(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            if (nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }

}
