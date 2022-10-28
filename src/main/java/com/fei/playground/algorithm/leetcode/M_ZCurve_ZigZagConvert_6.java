package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 * https://leetcode.cn/problems/zigzag-conversion/
 */
public class M_ZCurve_ZigZagConvert_6 {

    /**
     * 手撕一遍循环arrayList，模拟goingUpDown 86% 25%
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        ArrayList<StringBuilder> arr = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            arr.add(new StringBuilder());
        }

        int nextRow = 0;
        int maxRow = numRows - 1;
        boolean goingDown = true;
        for (int i = 0; i < s.length(); i++) {
            arr.get(nextRow).append(s.charAt(i));
            //换行换方向
            if (nextRow == 0 && !goingDown) {//改向下走
                goingDown = true;
            } else if (nextRow == maxRow && goingDown) {//改向上走
                goingDown = false;
            }
            nextRow = goingDown ? nextRow + 1 : nextRow - 1;
        }

        StringBuilder b = new StringBuilder();
        for (StringBuilder stringBuilder : arr) {
            b.append(stringBuilder);
        }

        return b.toString();
    }

}
