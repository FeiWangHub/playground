package com.fei.playground.algorithm.leetcode;

import java.util.HashSet;

/**
 * 694. 不同岛屿的数量
 * 给定一个非空 01 二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。
 * 请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。
 * https://leetcode.cn/problems/number-of-distinct-islands/
 */
public class M_Grid_DFS_NumOfDistinctIslands_694 {

    HashSet<Integer> shapes = new HashSet<>();
    int[][] grid;
    int rows = 0;
    int cols = 0;
    int curPath = 0;

    /**
     * 思路1 手撕int路径记录法 100% 63%
     * 路径记录法 一边把1变成0，一边用int记录上下左右路径
     * (还有一个思路，是每一个岛屿，把它最左上角的点，当成0,0，判断形状)
     */
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    curPath = 0;
                    infect(row, col, 0);
                    shapes.add(curPath);
                }
            }
        }
        return shapes.size();
    }

    public void infect(int row, int col, int direction) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] != 1) {
            return;
        }

        grid[row][col] = 0;
        curPath = curPath * 10 + direction;

        //上下左右
        infect(row - 1, col, 1);
        infect(row + 1, col, 2);
        infect(row, col - 1, 3);
        infect(row, col + 1, 4);

        curPath = curPath * 10 + 5;//代表某一个格子的上下左右的结束
    }
}
