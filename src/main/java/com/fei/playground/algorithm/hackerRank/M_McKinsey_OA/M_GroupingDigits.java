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
    }

}
