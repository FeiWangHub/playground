package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 18. 四数之和
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target。
 * 请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]
 * （若两个四元组元素一一对应，则认为两个四元组重复）
 * https://leetcode.cn/problems/4sum/
 */
public class M_Array_Four4Sum_18 {

    int i1, i2, i3, i4;
    int left, right, sum;

    List<List<Integer>> res = new LinkedList<>();

    /**
     * 手撕 手动去重 无剪枝 56% 31%
     * 去重思路：4个位置，每个位置它使用的数字，不能跟上一次相等
     * 思路：排序，锁定左边两个位置，然后右边的位置双指针，从左右两边向中间循环，判断sum
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 4; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            i1 = nums[i];
            if (i1 > 0 && target < 0) continue;//处理4个整数溢出的corner case
            for (int j = i + 1; j <= nums.length - 3; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                i2 = nums[j];
                //双指针循环剩下的
                left = j + 1;
                right = nums.length - 1;
                while (left < right) {
                    i3 = nums[left];
                    i4 = nums[right];
                    //滑动窗口
                    sum = i1 + i2 + i3 + i4;
                    if (sum == target) {//命中
                        res.add(Arrays.asList(i1, i2, i3, i4));

                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;

                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }

    HashSet<List<Integer>> set = new HashSet<>();

    /**
     * 手撕 set去重 5% 5%
     * 思路：排序，锁定左边两个位置，然后右边的位置双指针，从左右两边向中间循环，判断sum，set去重
     */
    public List<List<Integer>> fourSum_set(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 4; i++) {
            i1 = nums[i];
            if (i1 > 0 && target < 0) continue;//处理4个整数溢出的corner case
            for (int j = i + 1; j <= nums.length - 3; j++) {
                i2 = nums[j];
                //双指针循环剩下的
                left = j + 1;
                right = nums.length - 1;
                while (left < right) {
                    i3 = nums[left];
                    i4 = nums[right];
                    //滑动窗口
                    sum = i1 + i2 + i3 + i4;
                    if (sum == target) {//命中
                        set.add(Arrays.asList(i1, i2, i3, i4));
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return new LinkedList<>(set);
    }

    public static void main(String[] args) {
//        new M_Array_Four4Sum_18().fourSum(new int[]{0, 0, 0, 0}, 0).forEach(System.out::println);
        new M_Array_Four4Sum_18().fourSum(new int[]{2, 2, 2, 2, 2}, 8).forEach(System.out::println);
//        HashSet<List<Integer>> set = new HashSet<>();
//        set.add(Arrays.asList(2,2,2,2));
//        System.out.println(set.contains(Arrays.asList(2,2,2,2)));
    }

}
