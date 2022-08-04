package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 两个数组的交集
 * https://leetcode.cn/problems/intersection-of-two-arrays/
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。
 * 输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 */
public class E_Map_IntersectionOfTwoArray_349 {

    // 17.57% 81.55%
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }

        HashSet<Integer> matchSet = new HashSet<>();
        for (int i : nums2) {
            if (set.contains(i)) matchSet.add(i);
        }

        return matchSet.stream().mapToInt(Integer::intValue).toArray();
    }

    //精通Stream
    public int[] intersection_stream(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        return Arrays.stream(nums2).distinct().filter(set::contains).toArray();
    }


}
