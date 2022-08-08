package com.fei.playground.algorithm.leetcode;

/**
 * 连续子数组的最大和
 * https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 */
public class M_Array_MaxSubArraySum_53_Offer42 {

    /**
     * 100% 94% 通过 O(N)
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int curSum = 0;

        for(int i: nums){
            if(curSum > 0){
                curSum += i;
            }else{
                curSum = i;
            }
            max = Math.max(max, curSum);
        }

        return max;
    }

    /**
     *  201 / 202 个通过测试用例 1个超时
     */
    public int maxSubArray_brute(int[] nums) {
        //brute force
        int max = nums[0];
        int curSum = 0;

        for(int i=0; i < nums.length; i++){
            max = Math.max(max, nums[i]);
            curSum = nums[i];
            for(int j=i+1; j<nums.length; j++){
                curSum += nums[j];
                max = Math.max(max, curSum);
            }
        }
        return max;
    }

}
