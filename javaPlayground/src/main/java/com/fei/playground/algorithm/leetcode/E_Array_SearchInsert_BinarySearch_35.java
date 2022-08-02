package com.fei.playground.algorithm.leetcode;

public class E_Array_SearchInsert_BinarySearch_35 {

    public int searchInsert(int[] nums, int target) {
        //笨解法1
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur > target && i == 0) {
                return 0;
            } else if (cur == target) {
                return i;
            } else if (i != nums.length - 1 && cur < target && target < nums[i + 1]) {
                return i + 1;
            }
        }
        return nums.length;
    }

    public int searchInsert2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    public int searchInsert3(int[] nums, int target) {
        //二分法解法
        int left = 0;
        int right = nums.length - 1;

        int middle;
        int cur;
        while(left <= right){
            middle = left + (right - left) / 2;
            cur = nums[middle];
            if(cur == target){
                return middle;
            }else if(cur < target){//find right
                left = middle + 1;
            }else if(cur > target){//find left
                right = middle - 1;
            }
        }
        return right + 1;
    }

}
