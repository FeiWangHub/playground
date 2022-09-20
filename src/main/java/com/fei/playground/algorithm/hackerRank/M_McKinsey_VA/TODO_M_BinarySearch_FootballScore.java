package com.fei.playground.algorithm.hackerRank.M_McKinsey_VA;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 2022 Sep 20th OA第一题
 * The number of goals achieved by two football teams in matches in a
 * league is given in the form of two lists. For each match of team B,
 * compute the total number of matches of team A where team A has
 * scored less than or equal to the number of goals scored by team B in
 * that match.
 * <p>
 * Example
 * teamA = [1, 2, 31
 * teamB = [2, 47
 * Team A has played three matches and has scored teamA = [1, 2, 3] goals
 * in each match respectively. Team B has played two matches and has
 * scored teamB = [2, 4] goals in each match respectively. For 2 goals scored
 * by team B in its first match, team A has 2 matches with scores 1 and 2.
 * For 4 goals scored by team B in its second match, team A has 3 matches
 * with scores 1, 2 and 3. Hence, the answer is [2, 3].
 * <p>
 * Function Description
 * Complete the function counts in the editor below.
 * counts has the following parameter(s):
 * int teamA/n: first array of positive integers
 * int teamB[m]: second array of positive integers
 * <p>
 * Return
 * int teamA[n]: an array of m positive integers, one for each
 * int teamB[n]: representing the total number of elements from teamAli
 * satisfying teamAli]≤teamBli]where O <j <n and O < i < m, in the given
 * order.
 */
public class TODO_M_BinarySearch_FootballScore {

    /**
     * 二分法 不单单是可以找到"相等"的目标
     * 还可以找到"大于/小于某个值的 的分界线、临界点"，它找到的是low"第一个小于等于某临界值的点"
     */
    public static List<Integer> counts_binarySearch(List<Integer> teamA, List<Integer> teamB) {
        LinkedList<Integer> result = new LinkedList<>();

        //binary search version
        Collections.sort(teamA);
        for (int i = 0; i < teamB.size(); i++) {
            Integer target = teamB.get(i);
            int low = 0, high = teamA.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (teamA.get(mid) > target) {
                    high = mid - 1;//go left
                } else {
                    low = mid + 1;//go right
                }
            }
            result.add(low);
        }
        return result;
    }

    /**
     * 暴力解法 性能不行
     */
    public static List<Integer> counts_brute_force(List<Integer> teamA, List<Integer> teamB) {
        // Write your code here
        LinkedList<Integer> result = new LinkedList<>();
        for (Integer scoreB : teamB) {
            int count = 0;
            for (Integer scoreA : teamA) {
                if (scoreA <= scoreB) count++;
            }
            result.add(count);
        }
        return result;
    }

    /**
     * 多次用sdk的binary search解法
     * 性能也不行 必须手写binary search
     */
    public static List<Integer> counts(List<Integer> teamA, List<Integer> teamB) {
        LinkedList<Integer> result = new LinkedList<>();
        Integer[] teamAArray = new Integer[teamA.size()];
        teamA.toArray(teamAArray);
        Arrays.sort(teamAArray);//ASC
        int lenA = teamAArray.length;

        System.out.println("DEBUG: teamAArray: " + teamAArray);
        System.out.println("DEBUG: teamAArray[0]: " + teamAArray[0]);
        System.out.println("DEBUG: teamAArray[lenA]: " + teamAArray[lenA - 1]);

        for (Integer scoreB : teamB) {
            int count = 0;
            if (teamAArray[0] > scoreB) {
                result.add(count);
                continue;
            } else {
                System.out.println("DEBUG: scoreB: " + scoreB);
                int searchTarget = scoreB;
                int idx = Arrays.binarySearch(teamAArray, searchTarget);
                while (idx < 0) {
                    System.out.println("DEBUG: searchTarget: " + searchTarget);
                    searchTarget--;
                    idx = Arrays.binarySearch(teamAArray, searchTarget);
                }
                result.add(idx + 1);
            }
        }
        return result;
    }


}
