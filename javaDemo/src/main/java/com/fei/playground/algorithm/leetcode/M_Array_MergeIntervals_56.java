package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class M_Array_MergeIntervals_56 {

    /**
     * 官方排序解法
     * 按照gap左边数字排序，然后判断gap
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * 我写的第一版暴力解法 通过率77 / 169
     * 思路是一个一维数组，陈列出所有被占用的点，然后统计gap；有bug
     * 缺点是输入[[1,4],[5,6]]时，预期结果：[[1,4],[5,6]]， 我的结果[[1,6]]
     */
    public static int[][] merge_wrong(int[][] intervals) {

        //brute
        //1 loop find max
        //2 loop fill array
        //3 loop result, cal gaps

        int numGaps = intervals.length;
        int max = 0;
        for(int i=0; i<numGaps; i++){
            max = Math.max(max, intervals[i][1]);
        }
        boolean[] arr = new boolean[max+1];

        //arrays
        for(int i=0; i<numGaps; i++){
            Arrays.fill(arr, intervals[i][0] ,intervals[i][1]+1, true);
        }

        //计算开闭区间
        LinkedList<Integer> allGaps = new LinkedList<>();
        boolean lastVisited = false;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==lastVisited){
                continue;
            }

            if(arr[i]==true){//开始点
                allGaps.add(i);
                lastVisited = true;
            }else if(arr[i]==false){//结束点
                allGaps.add(i-1);
                lastVisited = false;
            }
        }
        //最后一个 如果是开区间 封闭它
        if(lastVisited) allGaps.add(arr.length-1);

        //整理开闭区间数据
        int[][] result = new int[allGaps.size()/2][2];
        for(int[] gap: result){
            gap[0]=allGaps.pop();
            gap[1]=allGaps.pop();
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));
    }

}
