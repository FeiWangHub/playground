package com.fei.playground.algorithm.leetcode;

/**
 * 41. 缺失的第一个正数
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * 输入：nums = [1,2,0] 输出：3
 * 输入：nums = [3,4,-1,1] 输出：2
 * 输入：nums = [7,8,9,11,12] 输出：1
 * https://leetcode.cn/problems/first-missing-positive/
 */
public class H_ArrayHash_firstMissingPositive_41 {

    /**
     * 原地哈希法 95.82% 53.71% 时间N 空间1
     * 三次循环
     * 1. 把所有小于等于0的数字，改为N+1
     * 2. 遍历，取当前数字绝对值(大于N的忽略)，把这个数字位置的数字，标记为负数
     * 3. 遍历，第一个正数位置+1即为答案；都是负数的话，答案是N+1
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; ++i) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 置换法 95% 46% 时间N 空间1
     * 1 遍历数组，如果nums[i]属于[1,n], 把它放到对应位置nums[i-1](注意避免死循环)
     * 2 再次遍历，如果位置上不是它该有的数字，就是它
     */
    public int firstMissingPositive_swap(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    /**
     * 手撕记忆arr法 100% 83% 时间N,空间N
     * 不符合空间1的原则
     */
    public int firstMissingPositive_mineArr(int[] nums) {
        boolean[] positiveShown = new boolean[nums.length + 1];//保存1->len-1个数
        for (int i : nums) {
            if (i <= 0 || i > positiveShown.length - 1) continue;
            positiveShown[i] = true;
        }

        for (int i = 1; i < positiveShown.length; i++) {
            if (!positiveShown[i]) return i;
        }
        return positiveShown.length;
    }
}
