package com.fei.playground.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 542. 01 矩阵
 * 给定一个由 0 和 1 组成的矩阵 mat，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1 。
 * https://leetcode.cn/problems/01-matrix/
 */
public class M_DP_BFS_Grid_DistanceToZero_542 {

    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * official BFS 50% 44%
     */
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dist = new int[m][n];
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<int[]>();
        // 将所有的 0 添加进初始队列中
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;
                }
            }
        }

        // 广度优先搜索，从已经访问过的位置，向上下左右传递1层；已经访问过的就不再访问了，因为它不会再更小了
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i = cell[0], j = cell[1];
            for (int d = 0; d < 4; ++d) {
                int ni = i + dirs[d][0];
                int nj = j + dirs[d][1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                    dist[ni][nj] = dist[i][j] + 1;
                    queue.offer(new int[]{ni, nj});
                    seen[ni][nj] = true;
                }
            }
        }

        return dist;
    }

    int[][] mat;
    int[][] res;
    int curIdx = 2;

    /**
     * TODO Bug
     */
    public int[][] updateMatrix_mineBug(int[][] mat) {
        //思路1 每碰上一个0，都从它开始，向周围涟漪，把它碰上的每一个1都渲染上level数字；碰上0不再渲染
        this.mat = mat;
        this.res = new int[mat.length][mat[0].length];
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                if (mat[row][col] == 0) {
                    curIdx++;
                    dfs(row - 1, col, 1);//up
                    dfs(row + 1, col, 1);//down
                    dfs(row, col - 1, 1);//left
                    dfs(row, col + 1, 1);//right
                }
                ;
            }
        }

        return this.res;
    }

    public void dfs(int row, int col, int level) {
        if (row < 0 || row >= mat.length
                || col < 0 || col >= mat[0].length
                || mat[row][col] == 0
                || mat[row][col] == curIdx) return;

        if (res[row][col] == 0) {
            res[row][col] = level;
        } else {
            res[row][col] = Math.min(level, res[row][col]);
        }

        mat[row][col] = curIdx;

        dfs(row - 1, col, level + 1);//up
        dfs(row + 1, col, level + 1);//down
        dfs(row, col - 1, level + 1);//left
        dfs(row, col + 1, level + 1);//right
    }

}
