package com.fei.playground.algorithm.leetcode;

/**
 * HSBC SHL online assessment
 * https://leetcode.cn/problems/rotate-string/solution/xuan-zhuan-zi-fu-chuan-by-leetcode-solut-4hlp/
 */
public class E_RotateString_HSBC_796 {

    /**
     * 我写的第一版substr拼接 26% 18%
     */
    public boolean rotateString(String s, String goal) {
        for(int i=0; i<s.length(); i++){
            String temp = s.substring(i) + s.substring(0,i);
            if(temp.equals(goal)){
                return true;
            }
        }
        return false;
    }

    /**
     * LC官方解法2 子字符串搜索法
     * 100% 73% BEST!
     */
    public boolean rotateString_substr(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    /**
     * LC官方解法1 逐个对比两个字符串的char
     * 100% 39%
     */
    public boolean rotateString_pointer(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (s.charAt((i + j) % n) != goal.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

}
