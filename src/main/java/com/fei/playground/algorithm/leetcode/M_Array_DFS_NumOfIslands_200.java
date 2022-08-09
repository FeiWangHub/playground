package com.fei.playground.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class M_Array_DFS_NumOfIslands_200 {

    int count = 0;
    int rows = 0;
    int cols = 0;

    /**
     * 60& 84% 逐个感染 DFS模式
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

    /**
     * 广度优先算法
     * 同样地，我们也可以使用广度优先搜索代替深度优先搜索。
     * 为了求出岛屿的数量，我们可以扫描整个二维网格。如果一个位置为 1，则将其加入队列，开始进行广度优先搜索。
     * 在广度优先搜索的过程中，每个搜索到的1, 都会被重新标记为0。直到队列为空，搜索结束。
     * 最终岛屿的数量就是我们进行广度优先搜索的次数。
     */
    public int numIslands_bfs(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;

        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    grid[r][c] = '0';
                    Queue<Integer> neighbors = new LinkedList<>();
                    neighbors.add(r * nc + c);
                    while (!neighbors.isEmpty()) {
                        int id = neighbors.remove();
                        int row = id / nc;
                        int col = id % nc;
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            neighbors.add((row - 1) * nc + col);
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < nr && grid[row + 1][col] == '1') {
                            neighbors.add((row + 1) * nc + col);
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            neighbors.add(row * nc + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < nc && grid[row][col + 1] == '1') {
                            neighbors.add(row * nc + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }

        return num_islands;
    }

}
