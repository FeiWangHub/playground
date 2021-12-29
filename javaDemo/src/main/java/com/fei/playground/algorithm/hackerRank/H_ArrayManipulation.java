package com.fei.playground.algorithm.hackerRank;

import java.util.List;

/**
 * https://www.hackerrank.com/challenges/crush/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */
public class H_ArrayManipulation {

    /**
     * 优化：只需要记录每个位置的加减记录
     * Complete the 'arrayManipulation' function below.
     * <p>
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER n
     * 2. 2D_INTEGER_ARRAY queries
     */
    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        long[] numbers = new long[n + 1];
        for (List<Integer> arr : queries) {
            numbers[arr.get(0) - 1] += arr.get(2);
            if (arr.get(1) != numbers.length)
                numbers[arr.get(1)] -= arr.get(2);
        }
        long maxval = 0;
        long valOfThisPosition = 0;
        for (long q : numbers) {
            valOfThisPosition += q;
            if (valOfThisPosition > maxval)
                maxval = valOfThisPosition;
        }
        return maxval;
    }

    //reach limit
    public static long arrayManipulation2(int n, List<List<Integer>> queries) {
        // Write your code here
        int max = 0;
        int[] array = new int[n];

        int a = 0, b = 0, k = 0;
        for (List<Integer> query : queries) {
            a = query.get(0);
            b = query.get(1);
            k = query.get(2);

            for (int i = a - 1; i < b; i++) {
                array[i] += k;
                max = Math.max(max, array[i]);
            }
        }

        return max;
    }
}
