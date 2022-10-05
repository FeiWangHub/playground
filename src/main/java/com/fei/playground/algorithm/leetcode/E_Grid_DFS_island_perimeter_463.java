package com.fei.playground.algorithm.leetcode;

/**
 * 岛屿的周长
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * https://leetcode.cn/problems/463/
 */
public class E_Grid_DFS_island_perimeter_463 {

    int count = 0;
    int rows;
    int cols;

    /**
     * 手写DFS 63% 92%
     * 时间复杂度O(m*n)
     * 空间复杂度O(m*n):  而栈空间最坏情况下会达到 O(m*n)
     */
    public int islandPerimeter(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    dfs(grid, row, col);
                    return count;
                }
            }
        }
        return count;
    }

    public void dfs(int[][] grid, int row, int col) {
        if (row >= rows || row < 0 || col >= cols || col < 0 || grid[row][col] == 0) {
            count++;
            return;
        }
        if (grid[row][col] == 2) {
            return;
        }

        grid[row][col] = 2;//visited

        //上下左右寻找
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    /**
     * 官方暴力 63% 5%
     * 遍历每一个格子，逐个计算它的周长
     */
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int islandPerimeter_brute(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; ++k) {
                        int tx = i + dx[k];
                        int ty = j + dy[k];
                        if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                            cnt += 1;
                        }
                    }
                    ans += cnt;
                }
            }
        }
        return ans;
    }
}
