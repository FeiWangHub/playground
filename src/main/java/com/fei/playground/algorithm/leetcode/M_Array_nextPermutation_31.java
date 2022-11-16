package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * 1 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 2 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 3 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 * 必须 原地 修改，只允许使用额外常数空间。
 * https://leetcode.cn/problems/next-permutation/
 */
public class M_Array_nextPermutation_31 {

    int biggerIdx = 0;

    /**
     * 34% 61% 时间n 空间1
     * 思路
     * 其实就是从数组倒着查找，找到nums[i] 比nums[i+1]小的时候，
     * 就将nums[i]跟nums[i+1]到nums[nums.length - 1]当中找到一个最小的比nums[i]大的元素交换。
     * 交换后，再把nums[i+1]到nums[nums.length-1]排序，就ok了
     */
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                findNextBiggerIdx(nums, i - 1);
                //System.out.println("符合条件的index:" + (i - 1) + "biggerIdx:" + biggerIdx);
                swap(nums, i - 1, biggerIdx);
                Arrays.sort(nums, i, nums.length);
                return;
            }
        }
        reverse(nums);
    }

    public int findNextBiggerIdx(int[] nums, int start) {
        biggerIdx = start + 1;
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[i] > nums[start] && nums[i] < nums[biggerIdx]) biggerIdx = i;
        }
        //System.out.println("比位置start:" + start + "起步，下一个比它刚刚好大的数字位置是" + biggerIdx);
        return biggerIdx;
    }

    public void reverse(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        new M_Array_nextPermutation_31().nextPermutation(new int[]{1, 2, 3});
    }

}
