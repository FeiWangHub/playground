package com.fei.playground.algorithm.leetcode;

/**
 * 59. 螺旋矩阵 II
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，
 * 且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * https://leetcode.cn/problems/spiral-matrix-ii/
 */
public class M_Grid_Spiral2_59 {

    /**
     * 手撕模拟 100% 11%
     * 左闭右闭的思路（其实左闭右开的话，代码更清晰）
     */
    public int[][] generateMatrix(int n) {
        int level = 1, row = 0, col = 0;
        int total = n * n;
        int next = 1;
        int[][] grid = new int[n][n];

        while (next <= total) {
            //right
            while (next <= total && col <= n - level) {
                grid[row][col++] = next++;
            }
            col--;
            row++;

            //down
            while (next <= total && row <= n - level) {
                grid[row++][col] = next++;
            }
            row--;
            col--;

            //left
            while (next <= total && col >= level - 1) {
                grid[row][col--] = next++;
            }
            col++;
            row--;

            //up
            while (next <= total && row >= level) {
                grid[row--][col] = next++;
            }
            row++;
            col++;

            level++;
        }

        return grid;
    }

}
