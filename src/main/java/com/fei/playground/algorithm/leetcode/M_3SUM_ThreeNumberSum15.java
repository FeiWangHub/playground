package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 双指针法，排序后，遍历右边O(N),总复杂度 排序NLogN + n平方
 * https://leetcode-cn.com/problems/3sum/
 */
public class M_3SUM_ThreeNumberSum15 {

    /**
     * 双指针法 并跳过重复value
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //双指针解法
        Arrays.sort(nums);//O(nLogn)
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            int cur = nums[i];
            if (nums[i] > 0) break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            if (i != 0 && cur == nums[i - 1]) continue;

            int start = i + 1;
            int end = len - 1;
            while (start < end) {
                int sum = cur + nums[start] + nums[end];
                if (sum == 0) {//match
                    result.add(Arrays.asList(cur, nums[start], nums[end]));
                    //keep moving and skip
                    start++;
                    while (start < end && nums[start] == nums[start - 1]) start++;
                    end--;
                    while (start < end && nums[end] == nums[end + 1]) end--;
                } else if (sum < 0) {//move left
                    start++;
                } else {//move right
                    end--;
                }
            }
        }
        return result;
    }

    public static List<List<Integer>> threeSum_brute(int[] nums) {
        //暴力破解法先 315 / 318 个通过测试用例
        List<List<Integer>> result = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(list);
                        if (!set.contains(list.toString())) {
                            result.add(list);
                            set.add(list.toString());
                        }
                        ;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 双指针法 重温
     * 76% 47%
     */
    public List<List<Integer>> threeSum_review(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//ASC

        //双指针
        int len = nums.length;
        int leftIdx = 0;
        int rightIdx = 0;
        int cur = 0;
        int sum = 0;
        for (int i = 0; i < len - 2; i++) {
            cur = nums[i];
            if (i != 0 && cur == nums[i - 1]) {
                continue;
            }
            if (cur > 0) break;

            leftIdx = i + 1;
            rightIdx = len - 1;
            while (leftIdx < rightIdx) {
                sum = nums[leftIdx] + nums[rightIdx] + cur;
                if (sum == 0) {
                    result.add(Arrays.asList(nums[leftIdx], cur, nums[rightIdx]));
                    leftIdx++;
                    while (leftIdx < rightIdx && nums[leftIdx] == nums[leftIdx-1]) leftIdx++;
                    rightIdx--;
                    while (leftIdx < rightIdx && nums[rightIdx] == nums[rightIdx+1]) rightIdx--;
                } else if (sum > 0) {
                    rightIdx--;
                } else {
                    leftIdx++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        threeSum(new int[]{-1, 0, 1});

    }

}
