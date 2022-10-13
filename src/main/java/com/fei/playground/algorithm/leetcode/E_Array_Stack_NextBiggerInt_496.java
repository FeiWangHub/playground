package com.fei.playground.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. 下一个更大元素 I
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 * https://leetcode.cn/problems/next-greater-element-i/
 * <p>
 * 加强版 503. 下一个更大元素 II https://leetcode.cn/problems/next-greater-element-ii/
 * 加强版 739 每日温度 https://leetcode.cn/problems/daily-temperatures/
 * 类似版 1475 商品折扣后的最终价格 https://leetcode.cn/circle/discuss/NWZtwL/
 * 终极版 42 接雨水
 *
 * 单调栈心得 https://leetcode.cn/circle/discuss/NWZtwL/
 * 单调栈很适用于"下一个更大的元素"之类问题
 */
public class E_Array_Stack_NextBiggerInt_496 {

    /**
     * 尝试单调栈解法 90% 15%
     * 时间复杂度O(m+n) 空间O(n)
     */
    public int[] nextGreaterElement_stack(int[] nums1, int[] nums2) {
        //从后向前遍历，
        Stack<Integer> stk = new Stack<>();
        Map<Integer, Integer> num2biggerNum = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && nums2[i] >= stk.peek()) {
                //那么，i位置左边的元素，它向右边第一大的元素，不可能是stk.peek()
                stk.pop();
            }
            num2biggerNum.put(nums2[i], stk.isEmpty() ? -1 : stk.peek());//found the bigger num of current
            stk.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = num2biggerNum.get(nums1[i]);
        }
        return nums1;
    }

    /**
     * 手撕暴力解法 98% 9.95%
     * 时间O(mn) 空间O(1)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //brute
        for (int i = 0; i < nums1.length; i++) {
            int x = nums1[i];
            int res = -1;

            int j = 0;
            while (nums2[j] != x) j++;

            //2 find bigger x
            for (int k = j + 1; k < nums2.length; k++) {
                if (nums2[k] > x) {
                    res = nums2[k];
                    break;
                }
            }
            nums1[i] = res;
        }
        return nums1;
    }

}
