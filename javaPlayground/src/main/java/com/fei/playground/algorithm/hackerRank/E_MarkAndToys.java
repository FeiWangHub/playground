package com.fei.playground.algorithm.hackerRank;

import java.util.*;

/**
 * Sorting
 * https://www.hackerrank.com/challenges/mark-and-toys/problem?h_l=interview&isFullScreen=true&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=sorting
 */
public class E_MarkAndToys {

    public static int maximumToys(List<Integer> prices, int k) {
        // Write your code here
        int cheapestPrice = 0;
        int cheapestIdx = 0;
        int gifts = 0;
        for(int i=0; i<prices.size(); i++){
            cheapestPrice = prices.get(i);
            for(int j=i+1;j<prices.size(); j++){
                if(cheapestPrice > prices.get(j)){
                    cheapestIdx = j;
                    cheapestPrice = prices.get(j);
                }
            }
            //buy it
            if(k>cheapestPrice){
                k-=cheapestPrice;
                gifts++;
            }else{
                return gifts;
            }

            //swap
            prices.set(cheapestIdx, prices.get(i));
            prices.set(i, cheapestPrice);
        }
        return gifts;
    }

}
