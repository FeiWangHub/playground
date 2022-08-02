package com.fei.playground.algorithm.hackerRank;

import java.util.HashMap;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=search
 */
public class M_SearchPriceMatch {

    public static void whatFlavors(List<Integer> cost, int money) {
        // Write your code here
        HashMap<Integer, Integer> cost2idx = new HashMap<>(cost.size());
        for (int i = 0; i < cost.size(); i++) {
            Integer c = cost.get(i);
            cost2idx.put(c, i + 1);
        }

        for (int i = 0; i < cost.size(); i++) {
            int val = cost.get(i);
            int match = money - val;
            if (cost2idx.containsKey(match)) {
                int idx = i+1;
                int matchIdx = cost2idx.get(match);
                if (idx < matchIdx) {
                    System.out.println(idx + "  " + matchIdx);
                } else {
                    System.out.println(matchIdx + "  " + idx);
                }
            }
        }
    }

}
