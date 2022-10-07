package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个整数数组nums，返回 数组answer，其中answer[i]等于nums中除nums[i]之外其余各元素的乘积。
 * 题目数据 保证 数组nums之中任意元素的全部前缀元素和后缀的乘积都在 32 位 整数范围内。
 * 请不要使用除法，且在O(n) 时间复杂度内完成此题。
 * https://leetcode.cn/problems/product-of-array-except-self/
 */
public class M_Array_Prefix_ProductOfArrayExceptSelf_238 {

    /**
     * 手撕左右缀的积,100% 45% 时/空复杂度O(n)
     * 一个数组记录左侧乘积
     * 一个数组记录右侧乘积
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] leftProduct = new int[nums.length];
        leftProduct[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            leftProduct[i] = nums[i - 1] * leftProduct[i - 1];
        }

        int[] rightProduct = new int[nums.length];
        rightProduct[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rightProduct[i] = nums[i + 1] * rightProduct[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = leftProduct[i] * rightProduct[i];
        }

        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));
    }

    /**
     * 官方 官方100% 54%
     * 时间O(n), 空间复杂O(1)解法
     */
    public int[] productExceptSelf_official(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] 表示索引 i 左侧所有元素的乘积
        // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R 为右侧所有元素的乘积
        // 刚开始右边没有元素，所以 R = 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {
            // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 R
            answer[i] = answer[i] * R;
            // R 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 R 上
            R *= nums[i];
        }
        return answer;
    }

}
