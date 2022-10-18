package com.fei.playground.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 字符串对比 极致
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若s 和 t中每个字符出现的次数都相同，则称s 和 t互为字母异位词。
 * https://leetcode.cn/problems/valid-anagram/
 */
public class E_String_Compare_isAnagram_242 {

    /**
     * char变数字count法 64% 34%
     */
    public boolean isAnagram_byCharIntArr(String s, String t) {
        int[] sCounts = new int[26];
        int[] tCounts = new int[26];
        for (char ch : s.toCharArray()) {
            sCounts[ch - 'a']++;
        }
        for (char ch : t.toCharArray()) {
            tCounts[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sCounts[i] != tCounts[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * char array 排序法 81% 47%
     */
    public boolean isAnagram_bySortArray(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    /**
     * 手撕哈希map法 64% 34%
     */
    public boolean isAnagram_hashmap(String s, String t) {
        if (s.length() != t.length()) return false;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        Integer temp;
        for (int i = 0; i < t.length(); i++) {
            temp = map.get(t.charAt(i));
            if (temp == null || temp == 0) {
                return false;
            }
            map.put(t.charAt(i), temp - 1);
        }

        return true;
    }

}
