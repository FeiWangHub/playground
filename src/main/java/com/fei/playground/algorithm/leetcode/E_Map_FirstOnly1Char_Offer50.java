package com.fei.playground.algorithm.leetcode;

import java.util.HashMap;

/**
 * https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 */
public class E_Map_FirstOnly1Char_Offer50 {

    /**
     * 24.81% 42.63%
     */
    public char firstUniqChar(String s) {
        HashMap<Character, Integer> c2count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            c2count.put(c, c2count.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c2count.get(c) == 1) {
                return c;
            }
        }

        return ' ';
    }

    /**
     * 78.81% 23.34% 用数组取代HashMap
     */
    public char firstUniqChar_byArray(String s) {
        if (s.equals("")) return ' ';
        //创建‘a'-'z'的字典
        int[] target = new int[26];
        //第一次遍历，将字符统计到字典数组
        for (int i = 0; i < s.length(); i++) {
            target[s.charAt(i) - 'a']++;
        }
        //第二次遍历，从字典数组获取次数
        for (int i = 0; i < s.length(); i++) {
            if (target[s.charAt(i) - 'a'] == 1) return s.charAt(i);
        }

        return ' ';
    }

    /**
     * 78.81% 23.34% 用数组取代HashMap
     */
    public char firstUniqChar_by(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch=s.charAt(i);

            //首次出现的位置是当前位置，且后面没有再出现这个字符
            if(s.indexOf(ch)==i&&s.indexOf(ch,i+1)==-1)
                return s.charAt(i);
        }
        return ' ';
    }

}
