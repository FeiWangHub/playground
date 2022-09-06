package com.fei.playground.algorithm.hackerRank.M_McKinsey_OA;

/**
 * Given an array of binary digits, 0 and 1, sort the array so that all zeros are at one end and all ones are at the other. Which end does not matter. To sort the array, swap any two adjacent elements. Determine the minimum number of swaps to sort the array.
 *
 * Example
 * arr = [0, 1, 0, 1]
 * With 1 move, switching elements 1 and 2 yields [0, 0, 1, 1], a sorted array
 *
 * Function Description
 * Complete the function minMoves
 *
 * minMoves has the following parameter(s):
 * int arr[n]: an array of binary digits
 *
 * Returns
 * int: the minimum number of moves necessarry
 *
 * Constraints
 *
 * 1 <= n <= 10^5
 * arr[i] is in the set {0, 1}
 * Another Example
 * arr = [1, 1, 1, 1, 0, 0, 0 0]
 * We return 0 because we do not need to swap any elements
 */
public class M_GroupingDigits {

    /**
     * 双指针解法，代码可优化
     */
    static int minMoves(int[] arr) {
        int ans1 = 0;
        int ans2 = 0;
        //first we try to put all 1's on the left side and 0's on the right
        int ptr = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 0) {
                ans1 += i - ptr;
                ++ptr;
            }
        }
        //then we try to put all 0's on the left and 1's on the right. Don't forget to put the pointer to 0
        ptr = 0;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == 1) {
                ans2 += i - ptr;
                ++ptr;
            }
        }
        //finally, we choose the smaller one
        return Math.min(ans1, ans2);
    }

    public static void main(String[] args) {
        int[] arr = {0,0,1,0,0,1};
        System.out.println(M_GroupingDigits.minMoves(arr));
//        System.out.println(M_GroupingDigits.minMovesNeedValid(arr));
    }

    /**
     * 未验证的python翻转解法
     */
//    def minMoves(arr):
//    count1 = 0
//    dis = 0
//            for num in arr:
//            if num ==1:
//    count1+=1
//            if num==0:
//    dis+=count1
//
//            count0 = len(arr) - count1
//    rev_dis = count1 * count0 - dis
//  return min(dis, rev_dis)

    /**
     * 尚未验证双循环解法
     * Java O(N)
     */
    public static int minMovesNeedValid(int[] input) {
        int n = input.length;
        if (n <= 1)
            return 0;
        int[] oneOrZeroAtLeft = new int[2];

        for (int k = 0; k<2; k++) {
            int first = 0;
            for (int i =0; i<n; i++) {
                if (input[i] == k) {
                    oneOrZeroAtLeft[k] += Math.abs(i-first);
                    first ++;
                }
            }
        }
        return Math.min(oneOrZeroAtLeft[0], oneOrZeroAtLeft[1]);
    }

}
