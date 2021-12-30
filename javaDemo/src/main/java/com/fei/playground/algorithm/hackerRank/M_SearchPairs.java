package com.fei.playground.algorithm.hackerRank;

import java.util.HashSet;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/pairs/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
 */
public class M_SearchPairs {

    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        int count = 0;
        HashSet<Integer> set = new HashSet<Integer>(arr);
        for(Integer i: arr){
            int bigger = k + i;
            if(set.contains(bigger)) count++;
        }
        System.out.println(count);
        return count;
    }
}
