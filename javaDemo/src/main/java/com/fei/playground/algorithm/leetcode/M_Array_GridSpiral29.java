package com.fei.playground.algorithm.leetcode;

/**
 * 顺时针打印二维数组
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 */
public class M_Array_GridSpiral29 {

    /**
     * 我的第一遍解法 比较笨 击败97 84
     * 其实判断某位置是否已经被访问过，可以用boolean[][]
     * 上下左右方向切换的时候，可以用 int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
     */
    public int[] spiralOrder(int[][] matrix) {
        final int lenX = matrix.length;
        if(lenX==0) return new int[0];


        final int lenY = matrix[0].length;
        int total = lenX * lenY;
        int count = 0;
        int[] result = new int[total];

        int x=0, y=-1;
        int circleIdx = 1;
        while(count!=total){
            //向右
            while(count!=total && y <= lenY-circleIdx-1){
                y++;
                result[count] = matrix[x][y];
                count++;
            }

            //向下
            while(count!=total && x <= lenX-circleIdx-1){
                x++;
                result[count] = matrix[x][y];
                count++;
            }

            //向左
            while(count!=total && y >= circleIdx){
                y--;
                result[count] = matrix[x][y];
                count++;
            }

            //向上
            while(count!=total && x >= circleIdx + 1){
                x--;
                result[count] = matrix[x][y];
                count++;
            }

            circleIdx++;
        }
        return result;

    }

}
