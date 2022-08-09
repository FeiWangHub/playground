package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/merge-sorted-array/
 * 给你两个按 非递减顺序 排列的整数数组nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。
 * 为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 */
public class E_Array_MergeTwoArray_88 {

    /**
     * 26% 52%
     * 时间 (m+n)log(m+n)
     * 空间 log(m+n)
     */
    public void merge_bySort(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * 倒插法
     * 100% 91%
     * 时间(m+n) 空间(1)
     */
    public void merge_byDoublePointer(int[] nums1, int m, int[] nums2, int n) {
        int i = m + n;
        while (m > 0 && n > 0) {
            nums1[--i] = nums1[m - 1] > nums2[n - 1] ? nums1[--m] : nums2[--n];
        }

        while (n > 0) {
            nums1[--i] = nums2[--n];
        }
    }
}
