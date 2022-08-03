package com.fei.playground.algorithm.leetcode;

/**
 * 判定字符是否唯一
 * https://leetcode.cn/problems/is-unique-lcci/
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 */
public class E_Map_HasUniqueChar {

    //hashset法就不写了
    public boolean isUnique(String astr) {
        //数组标示法
        int[] hash = new int[26];
        for(char ch : astr.toCharArray()) {
            hash[ch - 'a']++;
            if(hash[ch - 'a'] > 1) {
                return false;
            }
        }
        return true;
    }

}
