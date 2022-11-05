package com.fei.playground.algorithm.leetcode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 面试题 17.09. 第 k 个数
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。
 * 注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 * https://leetcode.cn/problems/get-kth-magic-number-lcci/
 */
public class M_DP_Heap_KthMagicNumber_TODO {

    /**
     * 官方Heap 40% 28%
     */
    public int getKthMagicNumber(int k) {
        int[] factors = {3, 5, 7};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int magic = 0;
        for (int i = 0; i < k; i++) {
            long curr = heap.poll();
            magic = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return magic;
    }

    /**
     * 官方DP 100% 93% 动态规划 TODO 需要学习
     * https://leetcode.cn/problems/get-kth-magic-number-lcci/solution/di-k-ge-shu-by-leetcode-solution-vzp7/
     */
    public int getKthMagicNumber_dp(int k) {
        int[] dp = new int[k + 1];
        dp[1] = 1;
        int p3 = 1, p5 = 1, p7 = 1;
        for (int i = 2; i <= k; i++) {
            int num3 = dp[p3] * 3, num5 = dp[p5] * 5, num7 = dp[p7] * 7;
            dp[i] = Math.min(Math.min(num3, num5), num7);
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
            if (dp[i] == num7) {
                p7++;
            }
        }
        return dp[k];
    }

    /**
     * 手撕Heap 40% 5%
     */
    public int getKthMagicNumber_mine(int k) {
        if (k == 1) return 1;
        int count = 0;

        PriorityQueue<Long> pq = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();
        long cur = 1;
        pq.offer(1L);

        while (true) {
            cur = pq.poll();
            set.add(cur);

            count++;
            //System.out.println("Count:" + count + " cur:" + cur);
            if (count == k) return (int) cur;
            if (set.add(cur * 3)) pq.offer(cur * 3);
            if (set.add(cur * 5)) pq.offer(cur * 5);
            if (set.add(cur * 7)) pq.offer(cur * 7);
        }
    }

    public static void main(String[] args) {
        M_DP_Heap_KthMagicNumber_TODO m = new M_DP_Heap_KthMagicNumber_TODO();
        System.out.println(m.getKthMagicNumber(643));
    }

}
