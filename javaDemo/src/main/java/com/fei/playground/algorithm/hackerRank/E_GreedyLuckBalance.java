package com.fei.playground.algorithm.hackerRank;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/luck-balance/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=greedy-algorithms
 */
public class E_GreedyLuckBalance {

    public static int luckBalance(int k, List<List<Integer>> contests) {
        List<Integer> importantLucks = new ArrayList<>(contests.size());
        int luckSum = 0;

        for(List<Integer> contest: contests){
            int luck = contest.get(0);
            int imp = contest.get(1);
            luckSum += luck;
            if(imp==1){
                importantLucks.add(luck);
            }
        }
        System.out.println("Total Sum: " + luckSum);

        Collections.sort(importantLucks);//DESC
        Collections.reverse(importantLucks);
        Iterator<Integer> it = importantLucks.iterator();
        while(it.hasNext()){
            int curr = it.next();
            if(k!=0){
                k--;
            }else{
                System.out.println("current to win: "+curr);
                luckSum -= (curr*2);
            }
        }
        return luckSum;
    }

    /**
     * 网友Priority Queue解法
     * Queue<Integer> heap = new PriorityQueue<>();
     * long luck = 0;
     * for(int i=0; i<N; i++) {
     *     if(T[i]!=1) luck+=L[i];
     *     else if(heap.size()<K) heap.add(L[i]);
     *     else if(K<1 || L[i]<=heap.peek()) luck-=L[i];
     *     else {luck-=heap.remove(); heap.add(L[i]);}
     * }
     * luck += heap.stream().mapToInt(i->i).sum();
     */
    public static int luckBalanceByOthers(int k, List<List<Integer>> contests) {
        int size = contests.size();

        int loseCount = 0;
        int luckCount = 0;
        Collections.sort(contests, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o2.get(0).compareTo(o1.get(0));
            }
        });

        for(int i = 0; i < size; i++)
        {
            if (contests.get(i).get(1) == 1)
            {
                if (loseCount < k)
                {
                    luckCount += contests.get(i).get(0);
                    loseCount++;
                }
                else{
                    luckCount -= contests.get(i).get(0);
                }
            }
            else{
                luckCount += contests.get(i).get(0);
            }
        }

        return luckCount;
    }

    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1,5,7,8,2);
        Collections.sort(l);
        Collections.reverse(l);

        System.out.println(l.toString());

        System.out.println(l.subList(0,3).stream().mapToInt(x->x).sum());
        System.out.println(l.subList(3,l.size()).stream().mapToInt(x->x).sum());
    }

}
