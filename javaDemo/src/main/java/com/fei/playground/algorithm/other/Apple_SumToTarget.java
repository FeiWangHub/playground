package com.fei.playground.algorithm.other;

import java.util.*;

// Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
// Each number in candidates may only be used once in the combination.
// Note: The solution set must not contain duplicate combinations.

// Example 1:
// Input: candidates = [10,1,2,7,6,1,5], target = 8
// Output:
// [
// [1,1,6],
// [1,2,5],
// [1,7],
// [2,6]
// ]
// Example 2:

// Input: candidates = [2,5,2,1,2], target = 5
// Output:
// [
// [1,2,2],
// [5]
// ]

// Constraints:

// 1 <= candidates.length <= 100
// 1 <= candidates[i] <= 50
// 1 <= target <= 30

/**
 * 2021 Jan 12th
 */
public class Apple_SumToTarget {

    public static void main(String[] args) {
        List<Integer> cans = Arrays.asList(1,2,3,4,5);
        List<List<Integer>> result = solution(cans, 3);
        System.out.println(result.toString());
    }

    static List<List<Integer>> result = new ArrayList<>();
    static LinkedList<Integer> curPath = new LinkedList();

    public static List<List<Integer>> solution(List<Integer> candidates, int target){
        Collections.sort(candidates);//ASC
        dfs(target, candidates);
        return result;
    }

    public static void dfs(int target, List<Integer> candidates){
        //exit
        if(target == 0){
            result.add(new ArrayList<Integer>(curPath));
            return;
        }else if(candidates.size()==0) return;

        //current state
        for(int i=0; i<candidates.size(); i++){
            int now = candidates.get(i);
            if(now > target) break;

            int balance = target - now;
            curPath.add(now);

            //next state
            if(balance!=0 && candidates.size() ==1) return;//no more candidates
            List<Integer> newCandidates = candidates.subList(i+1, candidates.size());
            dfs(balance, newCandidates);

            //restore current state
            curPath.removeLast();
        }
    }
}
