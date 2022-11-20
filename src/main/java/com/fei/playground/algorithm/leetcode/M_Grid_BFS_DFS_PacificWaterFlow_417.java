package com.fei.playground.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵heights，heights[r][c]表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * 返回网格坐标 result的 2D 列表 ，其中result[i] = [ri, ci]表示雨水从单元格 (ri, ci) 流动 既可流向太平洋也可流向大西洋 。
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 * https://leetcode.cn/problems/pacific-atlantic-water-flow/
 */
public class M_Grid_BFS_DFS_PacificWaterFlow_417 {

    List<List<Integer>> res = new LinkedList<>();
    boolean[][] pacificGrid;//left and up
    boolean[][] altanGrid;//right and down
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int[][] heights;
    int m, n;

    /**
     * 官方DFS 88% 53%
     */
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        this.m = heights.length;
        this.n = heights[0].length;
        boolean[][] pacificGrid = new boolean[m][n];
        boolean[][] altanGrid = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacificGrid);
        }
        for (int j = 1; j < n; j++) {
            dfs(0, j, pacificGrid);
        }
        for (int i = 0; i < m; i++) {
            dfs(i, n - 1, altanGrid);
        }
        for (int j = 0; j < n - 1; j++) {
            dfs(m - 1, j, altanGrid);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificGrid[i][j] && altanGrid[i][j]) {
                    List<Integer> cell = new ArrayList<Integer>();
                    cell.add(i);
                    cell.add(j);
                    res.add(cell);
                }
            }
        }
        return res;
    }

    public void dfs(int row, int col, boolean[][] ocean) {
        if (ocean[row][col]) {
            return;
        }
        ocean[row][col] = true;
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newCol = col + dir[1];
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && heights[newRow][newCol] >= heights[row][col]) {
                dfs(newRow, newCol, ocean);
            }
        }
    }

    int[] tempXY;
    int tempRow, tempCol;

    /**
     * BFS思路
     * 1 从太平洋的上/左边遍历，把每一个能到达的点，标记
     * 2 再从大西洋的右/下边遍历，把每一个能达到的点，标记，
     * 3 重合点位即为结果
     * (DFS原始思路 (思路一复杂度太高)遍历grid，针对每一个格子dfs，判断能否满足流入条件；如果可以，结果写入HashSet；
     * 之后的遍历，可以复用这个HashSet)
     */
    public List<List<Integer>> pacificAtlantic_mineBFS(int[][] heights) {
        this.heights = heights;
        pacificGrid = new boolean[heights.length][heights[0].length];
        altanGrid = new boolean[heights.length][heights[0].length];

        //1 从太平洋的上/左边遍历，把每一个能到达的点，标记
        LinkedList<int[]> reachedQueue = new LinkedList<>();//Queue中每个点都代表可以触及
        for (int col = 0; col < heights[0].length; col++) {
            reachedQueue.offer(new int[]{0, col});
            pacificGrid[0][col] = true;
        }
        for (int row = 0; row < heights.length; row++) {
            reachedQueue.offer(new int[]{row, 0});
            pacificGrid[row][0] = true;
        }

        while (!reachedQueue.isEmpty()) {
            dfs(reachedQueue, pacificGrid);
        }

//        for (boolean[] row : pacificGrid) {
//            System.out.println(Arrays.toString(row));
//        }

        //2 从大西洋的下/右边遍历，把每一个能到达的点，标记
        for (int col = 0; col < heights[0].length; col++) {//最下边一个row
            reachedQueue.offer(new int[]{heights.length - 1, col});
            altanGrid[heights.length - 1][col] = true;
        }
        for (int row = 0; row < heights.length; row++) {//最后边竖col
            reachedQueue.offer(new int[]{row, heights[0].length - 1});
            altanGrid[row][heights[0].length - 1] = true;
        }

        while (!reachedQueue.isEmpty()) {
            dfs(reachedQueue, altanGrid);
        }

//        for (boolean[] row : altanGrid) {
//            System.out.println(Arrays.toString(row));
//        }

        //3 重合点位即为结果
        for (int row = 0; row < pacificGrid.length; row++) {
            for (int col = 0; col < pacificGrid[0].length; col++) {
                if (pacificGrid[row][col] && altanGrid[row][col]) res.add(Arrays.asList(row, col));
            }
        }

        return res;
    }

    //尝试从一个已经到达的点，向高处流
    private void dfs(LinkedList<int[]> reachedQueue, boolean[][] oceanGrid) {
        tempXY = reachedQueue.poll();
        tempRow = tempXY[0];
        tempCol = tempXY[1];
        tryFlow(tempRow - 1, tempCol, heights[tempRow][tempCol], oceanGrid, reachedQueue);//up
        tryFlow(tempRow + 1, tempCol, heights[tempRow][tempCol], oceanGrid, reachedQueue);//down
        tryFlow(tempRow, tempCol - 1, heights[tempRow][tempCol], oceanGrid, reachedQueue);//left
        tryFlow(tempRow, tempCol + 1, heights[tempRow][tempCol], oceanGrid, reachedQueue);//right
    }

    //尝试向该点位流动，向高处流
    public void tryFlow(int row, int col, int parentVal, boolean[][] grid, LinkedList<int[]> pacQueue) {
        if (row < 0 || row > heights.length - 1
                || col < 0 || col > heights[0].length - 1
                || grid[row][col] || heights[row][col] < parentVal) return;
        grid[row][col] = true;
        pacQueue.push(new int[]{row, col});
    }

}
