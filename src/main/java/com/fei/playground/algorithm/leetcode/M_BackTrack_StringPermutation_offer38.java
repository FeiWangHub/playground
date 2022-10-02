package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
 */
public class M_BackTrack_StringPermutation_offer38 {

    List<String> res = new LinkedList<>();
    Set<String> set = new HashSet<String>();
    boolean[] vis;
    StringBuilder sb = new StringBuilder();

    /**
     * 使用boolean[]去重法 80% 28%
     *
     * 具体地，我们只要在递归函数中设定一个规则，保证在填每一个空位的时候重复字符只会被填入一次。
     * 具体地，我们首先对原字符串排序，保证相同的字符都相邻，在递归函数中，
     * 我们限制每次填入的字符一定是这个字符所在重复字符集合中「从左往右第一个未被填入的字符」，即如下的判断条件：
     * if (vis[j] || (j > 0 && !vis[j - 1] && s[j - 1] == s[j])) {
     *      continue;
     * }
     * 这个限制条件保证了对于重复的字符，我们一定是从左往右依次填入的空位中的
     */
    public String[] permutation2(String s) {
        vis = new boolean[s.length()];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        backtrack2(arr, 0);
        return res.toArray(new String[]{});
    }

    public void backtrack2(char[] chars, int curIdx) {
        if (curIdx == chars.length) {
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            //vis[i - 1]为同一树枝去重， !vis[i - 1]为同一树层去重
            //if (vis[i] || (i > 0 && chars[i] == chars[i - 1] && vis[i - 1])) {
            if (vis[i] || (i > 0 && chars[i] == chars[i - 1] && !vis[i - 1])) {
                continue;
            }

            sb.append(chars[i]);
            vis[i] = true;
            backtrack2(chars, curIdx + 1);
            vis[i] = false;
            sb.deleteCharAt(curIdx);
        }
    }

    /**
     * 手写第一版 使用set去重 26% 13#
     */
    public String[] permutation(String s) {
        vis = new boolean[s.length()];
        char[] arr = s.toCharArray();
        backtrack(arr, 0);
        res.addAll(set);
        return res.toArray(new String[]{});
    }

    public void backtrack(char[] chars, int curIdx) {
        if (curIdx == chars.length) {
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (vis[i]) continue;

            sb.append(chars[i]);
            vis[i] = true;
            backtrack(chars, curIdx + 1);
            vis[i] = false;
            sb.deleteCharAt(curIdx);
        }
    }

}
