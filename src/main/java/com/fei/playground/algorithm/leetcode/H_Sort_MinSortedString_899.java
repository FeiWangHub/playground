package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;

/**
 * 有序队列
 * https://leetcode.cn/problems/orderly-queue/
 * 给定一个字符串 s 和一个整数 k。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 * <p>
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串。
 */
public class H_Sort_MinSortedString_899 {

    /**
     * 官方题解
     * 当 K == 1 时， 只能循环移动每个元素，无法改变相对位置。因此只需要获取循环移动过程中字典序最小的序列。
     * 当 K > 1 时， 可以生成当前字符串的任意序列。因此将原字符串排序生成字典序最小的序列。
     */
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            String smallest = s;
            StringBuilder sb = new StringBuilder(s);
            int n = s.length();
            for (int i = 1; i < n; i++) {
                char c = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(c);
                if (sb.toString().compareTo(smallest) < 0) {
                    smallest = sb.toString();
                }
            }
            return smallest;
        } else {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            return new String(arr);
        }
    }

}
