package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 347. 前 K 个高频元素
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class M_Heap_QuickSort_TopKFrequentItem_347 {

    /**
     * 手撕heap 50% 9.5%
     * 时间 nLogK, 空间n
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> num2CountMap = new HashMap<>();
        for (int num : nums) {
            num2CountMap.put(num, num2CountMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        num2CountMap.forEach(
                (key, val) -> {
                    maxHeap.add(new int[]{key, val});
                    if (maxHeap.size() > k) maxHeap.poll();
                }
        );

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll()[0];
        }
        return res;
    }

    /**
     * 一行stream解法11% 11%
     */
    public int[] topKFrequent_heapByKarl(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return map.entrySet()
                .stream()
                .sorted((m1, m2) -> m2.getValue() - m1.getValue())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    /**
     * 官方MapEntry 50% 64%
     */
    public int[] topKFrequent_heapOfficial(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    /**
     * 官方快排 90% 88%
     * 时间N2 空间N
     * 原版的快速排序算法的平均时间复杂度为 O(N\log N)O(NlogN)。
     * 我们的算法中，每次只需在其中的一个分支递归即可，因此算法的平均时间复杂度降为 O(N)O(N)。
     */
    public int[] topKFrequent_officialQuickSort(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        List<int[]> values = new ArrayList<int[]>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        quickSort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void quickSort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start);

        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        Collections.swap(values, start, index);

        if (k <= index - start) {
            quickSort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            if (k > index - start + 1) {
                quickSort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }

}
