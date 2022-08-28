package com.fei.playground.algorithm.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class M_String_TopKFrequentWords_692_FlexPort {

    /**
     * 手写 77% 61%
     */
    public List<String> topKFrequent(String[] words, int k) {
        //1 建立words-count map，统计映射, 保留max
        HashMap<String, Integer> words2count = new HashMap<>();
        for (String s : words) {
            words2count.put(s, words2count.getOrDefault(s, 0) + 1);
        }

        //2 翻转映射为 count-words
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : words2count.entrySet()) {
            list.add(entry.getKey());
        }

        //count dsc, 字母asc
        list.sort((a, b) -> {
            if (words2count.get(a) > words2count.get(b)) {
                return -1;
            } else if (words2count.get(a) < words2count.get(b)) {
                return 1;
            } else {
                return a.compareTo(b);
            }
        });

        return list.subList(0, k);
    }

    /**
     * JAVA一句话版本
     * 5.5% 42.68%
     */
    public List<String> topKFrequent_(String[] words, int k) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    if (o1.getValue().equals(o2.getValue())) {
                        return o1.getKey().compareTo(o2.getKey());
                    } else {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                })
                .map(Map.Entry::getKey)
                .limit(k)
                .collect(Collectors.toList());
    }

    /**
     * Priority Queue解法 77.54/59.41%
     */
    public List<String> topKFrequent_priorityQueue(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String, Integer>>(new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry1.getValue() == entry2.getValue() ? entry2.getKey().compareTo(entry1.getKey()) : entry1.getValue() - entry2.getValue();
            }
        });
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> ret = new ArrayList<String>();
        while (!pq.isEmpty()) {
            ret.add(pq.poll().getKey());
        }
        Collections.reverse(ret);
        return ret;
    }

}
