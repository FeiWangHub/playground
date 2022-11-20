package com.fei.playground.algorithm.leetcode;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * https://leetcode.cn/problems/surrounded-regions/
 */
public class M_Grid_BFS_DFS_SurroundedRegions_130 {

    int n, m;

    /**
     * 官方DFS无额外数据结构版本 99% 23%
     * 时间m*n 空间m*n (栈空间消耗多)
     */
    public void solve_official(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    char[][] board;
    LinkedList<int[]> allOPoints = new LinkedList<>();
    LinkedList<int[]> allEdgeOPoints = new LinkedList<>();

    /**
     * 手撕先BFS所有O和Edge， 再DFS 17% 79% 时间 m*n 空间
     * 遍历整个grid，把所有o的坐标放进一个集合，把所有边界上的o放入另一集合
     * 遍历'边界o'集合，dfs，感染，改为N
     * (有改进空间，allEdgeOPoints没必要，直接碰上就dfs)
     */
    public void solve_mine(char[][] board) {
        this.board = board;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] == 'O') {
                    allOPoints.add(new int[]{row, col});
                    //判断是否是边缘
                    if (row == 0 || col == 0 || row == board.length - 1 || col == board[0].length - 1) {
                        allEdgeOPoints.add(new int[]{row, col});
                    }
                }
            }
        }

        //找到所有不应该被转换的O
        //int initialEdgeOSize = allEdgeOPoints.size();
        Iterator<int[]> it = allEdgeOPoints.iterator();
        int[] temp = null;
        while (it.hasNext()) {
            temp = it.next();
            dfs(temp[0], temp[1]);
        }

        //遍历0集合，O转为X，N转为O
        it = allOPoints.iterator();
        while (it.hasNext()) {
            temp = it.next();
            if (board[temp[0]][temp[1]] == 'O') {
                board[temp[0]][temp[1]] = 'X';
            } else {
                board[temp[0]][temp[1]] = 'O';
            }
        }
    }

    public void dfs(int row, int col) {
        if (row < 0 || row > board.length - 1
                || col < 0 || col > board[0].length - 1
                || board[row][col] != 'O') return;

        board[row][col] = 'N';

        dfs(row - 1, col);//up
        dfs(row + 1, col);//down
        dfs(row, col - 1);//left
        dfs(row, col + 1);//right
    }

}
