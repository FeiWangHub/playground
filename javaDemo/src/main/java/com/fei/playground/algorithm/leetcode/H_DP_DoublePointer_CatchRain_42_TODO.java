package com.fei.playground.algorithm.leetcode;

/**
 * TODO 好像常见 需要练
 * Trapping Rain Water
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class H_DP_DoublePointer_CatchRain_42 {

    /**
     * 官方 双指针解法
     */
    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }

}
