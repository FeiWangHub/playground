package com.fei.playground.algorithm.hackerRank;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * The absolute difference is the positive difference between two values  and , is written  or  and they are equal.
 * If  and , . Given an array of integers, find the minimum absolute difference between any two elements in the array.
 * https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
 */
public class E_GreedyMinDiff {

    public static int minimumAbsoluteDifference(List<Integer> arr) {
        // Write your code here
        Collections.sort(arr);
        Iterator<Integer> it = arr.iterator();

        int pre = it.next();
        int cur = it.next();
        int minDiff = Math.abs(cur - pre);

        while(it.hasNext()){
            pre = cur;
            cur = it.next();
            int diff = Math.abs(cur - pre);
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;


    }

}
