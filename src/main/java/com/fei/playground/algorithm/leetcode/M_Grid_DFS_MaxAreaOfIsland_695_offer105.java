package com.fei.playground.algorithm.leetcode;

/**
 * 岛屿的最大面积
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿是由一些相邻的1(代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * <p>
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * https://leetcode.cn/problems/max-area-of-island/
 */
public class M_Grid_DFS_MaxAreaOfIsland_695_offer105 {

    /**
     * 手写DFS 100% 54%
     * 时间空间都是O(m*n)
     */
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    max = Math.max(max, dfs(grid, row, col));
                }
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int row, int col) {
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0 || grid[row][col] != 1) {
            return 0;
        }

        grid[row][col] = 2;//visited

        return 1
                + dfs(grid, row - 1, col)
                + dfs(grid, row + 1, col)
                + dfs(grid, row, col - 1)
                + dfs(grid, row, col + 1);
    }

}
