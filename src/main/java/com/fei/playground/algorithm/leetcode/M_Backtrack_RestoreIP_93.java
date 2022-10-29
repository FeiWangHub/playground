package com.fei.playground.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 93. 复原 IP 地址
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，
 * 这些地址可以通过在 s 中插入'.' 来形成。你 不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * https://leetcode.cn/problems/restore-ip-addresses/
 */
public class M_Backtrack_RestoreIP_93 {

    StringBuilder curPath = new StringBuilder();
    List<String> res = new LinkedList<>();

    /**
     * 暴力回溯 95% 60%
     */
    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) return res;
        //思路一 backtrack，然后判断当前的result string是否valid
        backtrack(s, 0, 0, 0);
        return res;
    }

    public void backtrack(String s, int curIdx, int curDigitLen, int curDotCount) {
        if (curDotCount > 3) return;
        if (curDigitLen > 3) return;
        if (curDigitLen != 0 && !isValidIPPart(s.substring(curIdx - curDigitLen, curIdx))) return;
        if (curIdx == s.length()) {//finish
            if (curDotCount == 3) res.add(curPath.toString());
            return;
        }

        //put digit
        if (curDigitLen < 3) {
            curPath.append(s.charAt(curIdx));
            backtrack(s, curIdx + 1, curDigitLen + 1, curDotCount);
            curPath.deleteCharAt(curPath.length() - 1);
        }

        //put dot
        if (curDigitLen > 0 && curDotCount < 3) {
            curPath.append('.');
            backtrack(s, curIdx, 0, curDotCount + 1);
            curPath.deleteCharAt(curPath.length() - 1);
        }
    }

    public boolean isValidIP(String s) {
        String[] arr = s.split("\\.");
        if (arr.length != 4) return false;
        for (int i = 0; i < 4; i++) {
            if (!isValidIPPart(arr[i])) return false;
        }
        return true;
    }

    public boolean isValidIPPart(String part) {
        if (null == part || part.length() == 0 || part.length() > 3) return false;
        if (part.charAt(0) == '0' && part.length() > 1) return false;

        int digit = Integer.parseInt(part);
        if (digit < 0 || digit > 255) return false;

        return true;
    }

    public static void main(String[] args) {
        M_Backtrack_RestoreIP_93 m = new M_Backtrack_RestoreIP_93();
//        System.out.println(m.isValidIPPart("168@1"));
//        System.out.println(m.isValidIP("255.255.11.135"));
//        System.out.println(m.isValidIP("255.255.111.35"));
//        List<String> res = m.restoreIpAddresses("25525511135");
        List<String> res = m.restoreIpAddresses("101023");
        res.forEach(System.out::println);
    }

}
