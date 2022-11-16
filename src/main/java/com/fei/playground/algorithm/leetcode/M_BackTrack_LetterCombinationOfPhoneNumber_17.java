package com.fei.playground.algorithm.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class M_BackTrack_LetterCombinationOfPhoneNumber_17 {

    String digits;
    HashMap<Character, char[]> num2charsMap = new HashMap<>(8);
    List<String> res = new LinkedList<>();
    StringBuilder sb = new StringBuilder();

    /**
     * 手撕backtrack 50% 70% 时间3^m * 4^n (其实最坏是4^n，全是数字7和9)
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return res;
        this.digits = digits;
        initMap();
        backtrack(0);
        return res;
    }

    public void backtrack(int curIdx) {
        if (curIdx == digits.length()) {
            res.add(sb.toString());
            return;
        }

        char[] possibleChars = num2charsMap.get(digits.charAt(curIdx));
        for (char c : possibleChars) {
            sb.append(c);
            backtrack(curIdx + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void initMap() {
        num2charsMap.put('2', new char[]{'a', 'b', 'c'});
        num2charsMap.put('3', new char[]{'d', 'e', 'f'});
        num2charsMap.put('4', new char[]{'g', 'h', 'i'});
        num2charsMap.put('5', new char[]{'j', 'k', 'l'});
        num2charsMap.put('6', new char[]{'m', 'n', 'o'});
        num2charsMap.put('7', new char[]{'p', 'q', 'r', 's'});
        num2charsMap.put('8', new char[]{'t', 'u', 'v'});
        num2charsMap.put('9', new char[]{'w', 'x', 'y', 'z'});
    }
}
