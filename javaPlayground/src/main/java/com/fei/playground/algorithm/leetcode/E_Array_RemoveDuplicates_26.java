package com.fei.playground.algorithm.leetcode;

/**
 * 删除有序数组中的重复项
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class E_Array_RemoveDuplicates_26 {

    public int removeDuplicates(int[] nums) {
        int duplicatesFound = 0;
        int lastVisitedVal = nums[0];

        for(int i=1; i < nums.length; i++){
            int cur = nums[i];
            if(lastVisitedVal == cur){//duplicate
                duplicatesFound++;
            }else{//stay
                nums[i - duplicatesFound] = nums[i];
                lastVisitedVal = cur;
            }
        }

        return nums.length - duplicatesFound;
    }

}
