package com.fei.playground.algorithm.leetcode;

/**
 * Trapping Rain Water 接雨水
 * https://leetcode-cn.com/problems/trapping-rain-water/
 *
 * TODO 需要再尝试一下单调栈解法
 */
public class H_DP_DoublePointer_CatchRain_42 {

    /**
     * 手撕双指针 12.91% 63.41%
     * 尝试一下双指针 + 我的砍山头解法
     * 1.start指针，0起步，找第一个比自己高的
     * 2.end指针，从start起步，找>=start的值。
     * --2.1 end每经过一个低于start的，就在cache蓄水池加上start-cur的水滴
     * --2.2 end遇上>=start的，结算雨水，重置start为end位置
     * --2.3 end到结束都没有遇上>=start的，降低start位置高度，cache池清零，end重新出发; 如果高度为0都还不行，结束
     * 3.start触底，结束
     */
    public static int trap(int[] height) {
        int start = 0, end = 1, len = height.length;
        int cache = 0, pool = 0;

        while (start < len) {//程序终止条件
            while (end < len && height[end] < height[start]) {//2.1end向前推进
                cache += height[start] - height[end];
                end++;
            }

            if (end == len) {//2.3
                if (height[start] != 0) {
                    cache = 0;
                    end = start;
                    height[start]--;
                } else {
                    break;
                }
            } else {//2.2
                pool += cache;
                cache = 0;
                start = end++;
            }
        }
        return pool;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[]{4, 2, 3}));
    }

}
