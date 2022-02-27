package com.fei.playground.algorithm.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 2022.01.19
 * 很遗憾zhe'dao
 * input: k, n
 * output: find all combinations composed of 1 - 9 such that their size is k and sum is n
 * no repeat number.
 */
public class Apple_SumToTarget1to9 {

    static List<List<Integer>> result = new LinkedList<>();
    static LinkedList<Integer> curPath = new LinkedList<>();
    static int n;
    static int finalTarget;

    public static List<List<Integer>> solution(int target, int total) {
        n = total;
        finalTarget = target;
        dfs(0, 1);
        return result;
    }

    public static void dfs(int curSum, int next) {
        //exit
        if (curPath.size() > n) {
            return;
        } else if (curSum == finalTarget) {
            result.add(new ArrayList<>(curPath));
            return;
        } else if(next==10) return;

        //current state
        for (int i = next; i <= 9; i++) {
            if (i+curSum > finalTarget) break;

            curPath.add(i);

            //next state
            dfs(curSum+i, i+1);

            //restore current state
            curPath.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(10, 3).toString());
    }

}
