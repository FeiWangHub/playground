package com.fei.playground.algorithm.leetcode;

/**
 * 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * https://leetcode.cn/problems/container-with-most-water/submissions/
 */
public class M_DoublePointer_ContainerMostWater_11 {

    /**
     * 评论双指针 63% 82% 时间N，空间1
     * 一开始两个指针一个指向开头一个指向结尾，此时容器的底是最大的;
     * 接下来随着指针向内移动，会造成容器的底变小，在这种情况下想要让容器盛水变多，就只有在容器的高上下功夫。
     * 那我们该如何决策哪个指针移动呢？我们能够发现不管是左指针向右移动一位，还是右指针向左移动一位，容器的底都是一样的，都比原来减少了 1。
     * 这种情况下我们想要让指针移动后的容器面积增大，就要使移动后的容器的高尽量大，
     * 所以我们选择指针所指的高较小的那个指针进行移动，这样我们就保留了容器较高的那条边，放弃了较小的那条边，以获得有更高的边的机会。
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) {//左边落差更小
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

}
