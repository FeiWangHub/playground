package com.fei.playground.algorithm.leetcode;

/**
 * 计算二进制字符串
 * 给定一个字符串s，统计并返回具有相同数量 0 和 1 的非空（连续）子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是成组连续的。
 * 重复出现（不同位置）的子串也要统计它们出现的次数。
 *
 * 输入：s = "00110011"
 * 输出：6
 * 解释：6 个子串满足具有相同数量的连续 1 和 0 ："0011"、"01"、"1100"、"10"、"0011" 和 "01" 。
 * 注意，一些重复出现的子串（不同位置）要统计它们出现的次数。
 * 另外，"00110011" 不是有效的子串，因为所有的 0（还有 1 ）没有组合在一起。
 * https://leetcode-cn.com/problems/count-binary-substrings/
 */
public class E_String_countBinarySubStr_696 {

    public int countBinarySubstrings(String s) {
        //每一个位置面临的可能性
        //1. 跟上一个不同/或结束：结算本轮统计，开启新一轮统计，保留上一个连续循环的数字
        //2. 跟上一个相同: 当前长度++，但是仍在连续序列的开始中
        //结算方法： sum += Min(上一轮宽度,本轮宽度)，然后重置

        int sum = 0;
        int lastLen = 0;
        int curLen = 1;

        for(int i=1; i<s.length(); i++){
            if((s.charAt(i)!=s.charAt(i-1)) ){//diff char
                sum += Math.min(curLen, lastLen);
                lastLen = curLen;
                curLen = 1;
            }else{//same char
                curLen++;
            }
        }
        sum += Math.min(curLen, lastLen);
        return sum;
    }

}
