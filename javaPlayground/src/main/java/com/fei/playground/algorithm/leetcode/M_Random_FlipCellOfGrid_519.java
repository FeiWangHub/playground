package com.fei.playground.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 给你一个 m x n 的二元矩阵 matrix ，且所有值被初始化为 0 。
 * 请你设计一个算法，随机选取一个满足 matrix[i][j] == 0 的下标 (i, j) ，并将它的值变为 1 。
 * 所有满足 matrix[i][j] == 0 的下标 (i, j) 被选取的概率应当均等。
 */
public class M_Random_FlipCellOfGrid_519 {

    /**
     * 官方nb解法 数组映射
     * 把二维Grid映射为一维数组: 长m宽为n的 Grid[x][y] = Array[x * n + j]
     * 反向算坐标时: Array[idx] = Grid[idx / n][idx % n]
     */
    class Solution {
        Map<Integer, Integer> map = new HashMap<>();
        int m, n, total;
        Random rand = new Random();

        public Solution(int m, int n) {
            this.m = m;
            this.n = n;
            this.total = m * n;
        }

        public int[] flip() {
            int x = rand.nextInt(total);
            total--;
            // 查找位置 x 对应的映射
            int idx = map.getOrDefault(x, x);
            // 将位置 x 对应的映射设置为位置 total 对应的映射
            map.put(x, map.getOrDefault(total, total));
            return new int[]{idx / n, idx % n};
        }

        public void reset() {
            total = m * n;
            map.clear();
        }
    }

    /**
     * 我第一遍的蓄水池解法
     * 超时 因为每次flip都需要循环grid
     */
//    int count;
//    Random r = new Random();
//    boolean[][] grid;
//    int m,n;
//
//    public void solution1(int m, int n) {
//        this.m=m;
//        this.n=n;
//        //this.balance = this.m * this.n;
//        this.grid = new boolean[m][n];
//    }
//
//    public int[] flip() {
//        int x=0,y=0;
//
//        for(int i=0;i<m; i++){
//            for(int j=0;j<n; j++){
//                if(!grid[i][j]){
//                    count++;
//                    if(r.nextInt(count)==0){
//                        x=i;
//                        y=j;
//                    }
//                }
//            }
//        }
//        grid[x][y] = true;
//        count=0;
//        return new int[]{x,y};
//    }
//
//    public void reset() {
//        //this.balance = this.m * this.n;
//        this.count=0;
//        this.grid = new boolean[m][n];
//    }

}
