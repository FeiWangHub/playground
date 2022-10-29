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
     * 手撕一遍循环arrayList，模拟goingUpDown 86% 55%
     * 思路是模拟Z、N的走向，碰到numRows时要上下反弹方向，即可
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;

        ArrayList<StringBuilder> arr = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) arr.add(new StringBuilder());

        int nextRow = 0;
        boolean goingDown = true;
        for (int i = 0; i < s.length(); i++) {
            arr.get(nextRow).append(s.charAt(i));
            //换行换方向
            if (nextRow == 0 && !goingDown) {//改向下走
                goingDown = true;
            } else if (nextRow == numRows - 1 && goingDown) {//改向上走
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

    /**
     * 模拟二维矩阵解法 20% 10%
     */
    public String convert_mockGrid(String s, int numRows) {
        int n = s.length(), r = numRows;
        if (r == 1 || r >= n) {
            return s;
        }
        int t = r * 2 - 2;
        int c = (n + t - 1) / t * (r - 1);
        char[][] mat = new char[r][c];
        for (int i = 0, x = 0, y = 0; i < n; ++i) {
            mat[x][y] = s.charAt(i);
            if (i % t < r - 1) {
                ++x; // 向下移动
            } else {
                --x;
                ++y; // 向右上移动
            }
        }
        StringBuffer ans = new StringBuffer();
        for (char[] row : mat) {
            for (char ch : row) {
                if (ch != 0) {
                    ans.append(ch);
                }
            }
        }
        return ans.toString();
    }

}
