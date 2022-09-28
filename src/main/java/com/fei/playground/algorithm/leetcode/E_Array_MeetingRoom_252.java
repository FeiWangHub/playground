package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;
/**
 * https://leetcode.cn/problems/meeting-rooms/
 * 会议室
 * 给定一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi]，
 * 请你判断一个人是否能够参加这里面的全部会议。
 */
public class E_Array_MeetingRoom_252 {

    /**
     * 手写排序法 97% 10%
     */
    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int[] arr : intervals) {
            System.out.println(Arrays.toString(arr));
        }

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{7, 10}, {2, 4}};
        System.out.println(canAttendMeetings(intervals));
    }

}
