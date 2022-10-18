package com.fei.playground.algorithm.leetcode;

/**
 * 1209. 删除字符串中的所有相邻重复项 II
 * 给你一个字符串s，「k 倍重复项删除操作」将会从 s中选择k个相邻且相等的字母，并删除它们，使被删去的字符串的左侧和右侧连在一起。
 * 你需要对s重复进行无限次这样的删除操作，直到无法继续为止。
 * 在执行完所有删除操作后，返回最终得到的字符串。
 * 本题答案保证唯一。
 * https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string-ii/
 */
public class M_String_deleteAdjacentCharOfString_1209 {

    /**
     * 官方2记忆计数法 79% 70% 时间空间O(N) 巧妙!
     * 如果为每个字符设置计数器，就不必每次删除完字符后从头开始。这种方法具有线性复杂度，但需要额外空间存储字符的计数器
     * 1 初始长度为 n 的数组 counts。
     * 2 遍历字符串：
     * 2.1 如果当前字符与上一个字符相等，令 counts[i] = counts[i - 1] + 1。
     * 2.1 否则，令 counts[i] = 1。
     * 2.2 如果 counts[i] = k，删除这 k 个字符，令 i = i - k。
     */
    public static String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int count[] = new int[sb.length()];
        for (int i = 0; i < sb.length(); ++i) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                count[i] = 1;
            } else {
                count[i] = count[i - 1] + 1;
                if (count[i] == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return sb.toString();
    }

    /**
     * 官方1暴力超时 时间n平方/k，空间1
     */
    public static String removeDuplicates_brute(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int len = -1;
        while (len != sb.length()) {//代表上次有删除操作
            len = sb.length();
            for (int i = 0, count = 1; i < sb.length(); i++) {
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {//不同字符
                    count = 1;
                } else if (++count == k) {//相同字符
                    sb.delete(i - k + 1, i + 1);
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
//        System.out.println(removeDuplicates("pbbcggttciiippooaais", 2));
        System.out.println(removeDuplicates("yfttttfbbbbnnnnffbgffffgbbbbgssssgthyyyy", 4));
//        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
    }

}
