package com.fei.playground.algorithm.company.HSBC.creativeLab;

/**
 * A mouse is placed in a maze. There is
 * a huge chunk of cheese somewhere
 * in the maze. The maze is
 * represented as an N x M grid of
 * integers where O represents a wall,
 * represents the path where the
 * mouse can move and 9 represents
 * the chunk of cheese. The mouse
 * starts at the top left corner at (0,0).
 * Write an algorithm to output 1 if the
 * mouse can reach the chunk of
 * cheese, else output 0.
 *
 * example:
 * 111
 * 911
 * 010
 * output: 1
 *
 * example:
 * 000
 * 911
 * 011
 * output : 0
 *
 * 我审题错了，它要求有路径就返回1，没有就0， 我返回了count
 */
public class M_Grid_DFS_findCheese {

    static int count = 0;
    static int rows = 0;
    static int cols = 0;

    //Q2
    public static int chunkOfCheese(int[][] maze) {
        // Write your code here
        rows = maze.length;
        cols = maze[0].length;
        bfsSearch(0, 0, maze);
        return count;
    }

    public static void bfsSearch(int row, int col, int[][] maze) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || maze[row][col] == 0) {
            return;
        }

        if (maze[row][col] == 9) {
            count++;
        }
        maze[row][col] = 0;

        bfsSearch(row - 1, col, maze);
        bfsSearch(row + 1, col, maze);
        bfsSearch(row, col - 1, maze);
        bfsSearch(row, col + 1, maze);
    }
}
