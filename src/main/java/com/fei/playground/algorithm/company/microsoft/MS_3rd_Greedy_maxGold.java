package com.fei.playground.algorithm.company.microsoft;

/**
 * 2022.01.28
 * find max gold sum
 */
public class MS_3rd_Greedy_maxGold {

    public void maxGold(int[][] grid) {
        int len = grid[0].length;
        int max = 0;
        for (int rowID = 1; rowID < len; rowID++) {
            for (int colID = 0; colID < len; colID++) {
                //left border
                if (colID == 0) {
                    grid[rowID][colID] = grid[rowID][colID] + Math.max(grid[rowID - 1][colID], grid[rowID - 1][colID + 1]);
                } else if (colID == len - 1) {
                    //right border
                    grid[rowID][colID] = grid[rowID][colID] + Math.max(grid[rowID - 1][colID], grid[rowID - 1][colID - 1]);
                } else {
                    grid[rowID][colID] = grid[rowID][colID] +
                            Math.max(
                                    Math.max(grid[rowID - 1][colID], grid[rowID - 1][colID + 1]),
                                    grid[rowID - 1][colID - 1]
                            );
                }

                if (rowID == len - 1) max = Math.max(max, grid[rowID][colID]);
            }
        }
        System.out.println(max);
    }

}
