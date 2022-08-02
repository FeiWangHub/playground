package com.fei.playground.algorithm.hackerRank;

import java.util.Arrays;

/**
 * https://www.hackerrank.com/challenges/max-array-sum/forum?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
 *
 * DP问题要善于用累加的思想，其实核心就是去比较上一个自己和每个间隔和自己相加的最大值
 *
 * To address with DP, work through the array, keeping track of the max at each position until you get to the last value of the array. You should start with the base cases defined before iterating through the remainder of the array.
 * max @ position 0: value @ 0
 * max @ position 1: either:
 * value @ 0
 * value @ 1
 * from that point forward, the max of the next position is either:
 * the current value at that position
 * the max value found so far
 * the max value from 2 positions back plus the current value
 * keep track of the max at each position until you get to the last position of the array
 */
public class M_DP_MaxArraySum {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int[] result = new int[arr.length];
        int curMax = 0;

        for(int i=0; i<arr.length; i++){
            if(i==0){
                curMax = arr[i];
            }else if(i==1){
                curMax = Math.max(arr[1], arr[0]);
            }else{
                //每一位上，它的最大值只有3中可能，他自己 | 他自己+前一个最大 | 前一个的最大
                //1.itself  2.i+result[i-2] 3.result[i-1]
                curMax=Math.max(arr[i], arr[i] + result[i-2]);
                curMax=Math.max(curMax, result[i-1]);
            }
            result[i] = curMax;
        }

        System.out.println(Arrays.toString(result));;
        return result[result.length-1];
    }

    public static void main(String[] args) {
        int[] arr = {2,1,5,8,4};
        System.out.println(maxSubsetSum(arr));
    }

}
