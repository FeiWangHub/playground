package com.fei.playground.algorithm.leetcode;

import java.util.LinkedList;

/**
 * 994. 腐烂的橘子
 * 在给定的m x n网格grid中，每个单元格可以有以下三个值之一：
 * 值0代表空单元格；
 * 值1代表新鲜橘子；
 * 值2代表腐烂的橘子。
 * 每分钟，腐烂的橘子周围4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回-1。
 * https://leetcode.cn/problems/rotting-oranges/
 */
public class M_Grid_BFS_DFS_RottingOrange_994 {

    int[][] grid;
    int goodCount = 0;
    int minutes = 0;
    LinkedList<int[]> badQueue = new LinkedList<>();

    /**
     * 手撕BFS+DFS 100% 26%
     * 思路 完整循环，把途径的每一个腐败橘子加入queue；循环queue，把它周围的腐败，被腐败的加入queue；
     * 直到queue被清空位置；（也许结束时，需要检查是否还剩下有没被感染的橘子）
     */
    public int orangesRotting(int[][] grid) {
        this.grid = grid;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    goodCount++;
                } else if (grid[row][col] == 2) {
                    badQueue.add(new int[]{row, col});
                }
            }
        }
        if (goodCount == 0) return 0;
        if (badQueue.isEmpty()) return -1;

        //System.out.println("初始坏橘子总数为:" + badQueue.size() + " 好橘子总数:" + goodCount);
        int tempSize = 0;
        int tempGoodCount = 0;
        while (!badQueue.isEmpty() && goodCount != 0) {
            //尝试走一秒
            tempSize = badQueue.size();
            tempGoodCount = this.goodCount;
            while (tempSize != 0) {
                int[] xy = badQueue.pollFirst();
                tempSize--;
                dfsInfect(xy[0] - 1, xy[1]);//up
                dfsInfect(xy[0] + 1, xy[1]);//down
                dfsInfect(xy[0], xy[1] - 1);//left
                dfsInfect(xy[0], xy[1] + 1);//right
            }

            //System.out.println("这一秒钟，感染前/后好橘子数量为:" + tempGoodCount +"/" + goodCount);
            if (tempGoodCount == this.goodCount) {//这一秒没腐败任何东西，结束游戏
                break;
            } else {
                minutes++;
            }
        }

        if (goodCount != 0) return -1;//有剩余橘子无法被感染
        return minutes;
    }

    //尝试腐败目标位置,返回是否成功腐败
    public void dfsInfect(int row, int col) {
        if (row < 0 || row >= grid.length
                || col < 0 || col >= grid[0].length
                || grid[row][col] != 1) return;

        grid[row][col] = 2;
        goodCount--;
        badQueue.offerLast(new int[]{row, col});
    }
}
