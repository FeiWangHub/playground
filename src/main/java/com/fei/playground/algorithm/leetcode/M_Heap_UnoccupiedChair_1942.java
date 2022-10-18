package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 1942. 最小未被占据椅子的编号
 * 有 n个朋友在举办一个派对，这些朋友从 0到 n - 1编号。派对里有 无数张椅子，编号为 0到 infinity。
 * 当一个朋友到达派对时，他会占据编号最小且未被占据的椅子。
 * ---
 * 比方说，当一个朋友到达时，如果椅子0，1和5被占据了，那么他会占据2号椅子。
 * 当一个朋友离开派对时，他的椅子会立刻变成未占据状态。如果同一时刻有另一个朋友到达，可以立即占据这张椅子。
 * ---
 * 给你一个下标从 0开始的二维整数数组times，其中times[i] = [arrivali, leavingi]表示第 i个朋友到达和离开的时刻，同时给你一个整数 targetFriend。所有到达时间 互不相同。
 * ---
 * 请你返回编号为 targetFriend的朋友占据的 椅子编号。
 * https://leetcode.cn/problems/the-number-of-the-smallest-unoccupied-chair/
 */
public class M_Heap_UnoccupiedChair_1942 {

    /**
     * 手撕 99% 5%
     * 时间n * logN, 空间 n
     */
    PriorityQueue<Integer> freeChairHeap = new PriorityQueue<>();
    PriorityQueue<Friend> friendHeap;
    int nextMaxSeat = 0;
    int targetFriendStartTime;

    public int smallestChair(int[][] times, int targetFriend) {
        //1 建立已经arrive的朋友heap，根据离开时间asc排序，好判断离开时间
        //2 根据arrive time，asc，排序times，
        //3 遍历排序好的index，可以遍历判断每个人能拿到的index
        //4 用一个最小堆heap维护空椅子idx
        friendHeap = new PriorityQueue<>(times.length, (o1, o2) -> {
            if (o1.endTime != o2.endTime) {
                return o1.endTime - o2.endTime;
            } else {
                return o1.arriveTime - o2.arriveTime;
            }
        });

        this.targetFriendStartTime = times[targetFriend][0];
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        for (int[] time : times) {
            //新人来了，给它椅子，看给新的还是旧的
            int curStart = time[0];
            int curEnd = time[1];

            //更新空闲椅子池
            while (friendHeap.peek() != null && curStart >= friendHeap.peek().endTime) {
                freeChairHeap.add(friendHeap.poll().chairIdx);
            }

            int chairId = !freeChairHeap.isEmpty() ? freeChairHeap.poll() : nextMaxSeat++;
            if (curStart == targetFriendStartTime) {
                return chairId;
            }

            friendHeap.add(new Friend(curStart, curEnd, chairId));
            //System.out.println("当前heap size:" + heap.size() + " top arriveTime:" + heap.peek().arriveTime);
        }

        return -1;
    }

    public static class Friend {
        public int chairIdx = -1;
        public int arriveTime;
        public int endTime;

        public Friend(int arriveTime, int endTime, int chairIdx) {
            this.arriveTime = arriveTime;
            this.endTime = endTime;
            this.chairIdx = chairIdx;
        }
    }

    public static void main(String[] args) {
        M_Heap_UnoccupiedChair_1942 t = new M_Heap_UnoccupiedChair_1942();
        System.out.println(t.smallestChair(new int[][]{{1, 4}, {2, 3}, {4, 6}}, 1));
    }

    /**
     * 评论区精简版 74% 53%
     * 链接：https://leetcode.cn/problems/the-number-of-the-smallest-unoccupied-chair/solution/-by-yu-niang-niang-y13f/
     */
    public int smallestChair_simple(int[][] times, int targetFriend) {
        int n = times.length;
        int[][] timeFriend = new int[n][3];
        for (int i = 0; i < n; i++) {
            timeFriend[i][0] = times[i][0];
            timeFriend[i][1] = times[i][1];
            timeFriend[i][2] = i;
        }

        Arrays.sort(timeFriend, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> waitChairs = new PriorityQueue<>();
        for (int i = 0; i < n; i++) waitChairs.offer(i);
        for (int[] friend : timeFriend) {
            while (!pq.isEmpty() && pq.peek()[1] <= friend[0]) {
                waitChairs.offer(pq.poll()[0]);
            }
            if (friend[2] == targetFriend)
                return waitChairs.peek();
            pq.offer(new int[]{waitChairs.poll(), friend[1]});
        }
        return 0;
    }

}
