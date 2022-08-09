package com.fei.playground.algorithm.leetcode;

/**
 * 岛屿数量
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class M_Array_NumOfIslands_200 {

    int count = 0;
    int rows = 0;
    int cols = 0;

    /**
     * 60& 84% 逐个感染
     */
    public int numIslands(char[][] grid) {
        rows = grid.length;
        cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == '1') {
                    count++;
                    infect(row, col, grid);
                }
            }
        }
        return count;
    }

    //输入坐标值为1，感染它周围上下左右所有的点
    public void infect(int row, int col, char[][] grid) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != '1') {
            return;
        }
        ;
        grid[row][col] = '2';

        //上下左右
        infect(row - 1, col, grid);
        infect(row + 1, col, grid);
        infect(row, col - 1, grid);
        infect(row, col + 1, grid);
    }
}
