package com.fei.playground.algorithm.company;

/**
 * Azure第一面
 */
public class MS_Azure_Array_findIntersection {

    public static void main(String[] args) {
        //input: 2 int array, ASC
        //output: print intersection, no repeat, no lib, optimize
        int[] arr1 = {};
        int[] arr2 = {1, 2, 3, 4, 5};
        solution(arr1, arr2);
    }

    /**
     * input: 2 int array, ASC
     * output: print intersection, no repeat, no lib, optimize
     */
    public static void solution(int[] arr1, int[] arr2) {
        Integer lastMatched = null;
        int len1 = arr1.length;
        int len2 = arr2.length;

        int idx1 = 0, idx2 = 0;
        while (idx1 < len1 && idx2 < len2) {
            if (arr1[idx1] == arr2[idx2]) {
                if (lastMatched == null || arr1[idx1] != lastMatched) {
                    System.out.println(arr1[idx1]);
                    lastMatched = arr1[idx1];
                }
                idx1++;
                idx2++;
            } else if (arr1[idx1] > arr2[idx2]) {
                idx2++;
            } else {
                idx1++;
            }
        }
    }

}
