package com.fei.playground.algorithm.hackerRank;

import java.util.List;

/**
 * 你只需要检查前边有几个人比你大，他们都贿赂过你
 * 检查的时候，不用全部扫描，只扫描你自己活动范围之内的
 * https://www.hackerrank.com/challenges/new-year-chaos/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
 */
public class M_NewYearChaos {

    //correct, but reach limit
    public static void minimumBribes(List<Integer> q) {
        // Write your code here
        int beBribed=0;
        for(int i=0; i<q.size(); i++){
            int expect = i+1;
            int actual = q.get(i);
            if(actual - expect > 2){
                System.out.println("Too chaotic");
                return;
            }else{
                //check people in front of me
                for (int j = Math.max(0, actual-2); j < i; j++) {
                    if(q.get(j) > actual) beBribed++;
                }
            }
        }
        System.out.println(beBribed);
    }


    //correct, but reach limit
    public static void minimumBribes2(List<Integer> q) {
        // Write your code here
        int beBribed=0;
        for(int i=0; i<q.size(); i++){
            int expect = i+1;
            int actual = q.get(i);
            if(actual - expect > 2){
                System.out.println("Too chaotic");
                return;
            }else{
                //check people in front of me
                for (int j = 0; j < i; j++) {
                    if(q.get(j) > actual) beBribed++;
                }
            }
        }
        System.out.println(beBribed);
    }
}
