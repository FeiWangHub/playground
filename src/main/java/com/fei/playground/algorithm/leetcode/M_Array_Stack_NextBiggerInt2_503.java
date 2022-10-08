package com.fei.playground.algorithm.leetcode;

import java.util.Stack;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组nums（nums[nums.length - 1]的下一个元素是nums[0]），返回nums中每个元素的下一个更大元素 。
 * 数字x的下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 * 如果不存在，则输出 -1。
 * https://leetcode.cn/problems/next-greater-element-ii/
 *
 * 实现循环数组还有2个思路，一个还是把数组复制到末尾一份，一个是取模把下标取模
 */
public class M_Array_Stack_NextBiggerInt2_503 {

    /**
     * 手撕 单调栈解法 从后向前循环 29% 88%
     * 时间N，空间N
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Stack<Integer> stk = new Stack<>();

        //因为是循环数组 先初始化一下stack
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && nums[i] >= stk.peek()) {
                stk.pop();
            }
            stk.push(nums[i]);
        }

        //构建answer
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && nums[i] >= stk.peek()) {
                stk.pop();
            }
            ans[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(nums[i]);
        }

        return ans;
    }

}
