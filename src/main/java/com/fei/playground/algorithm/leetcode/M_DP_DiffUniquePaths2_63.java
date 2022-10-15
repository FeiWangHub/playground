package com.fei.playground.algorithm.leetcode;

/**
 * 63. 不同路径 II
 * 一个机器人位于一个m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * https://leetcode.cn/problems/unique-paths-ii/
 */
public class M_DP_DiffUniquePaths2_63 {

    /**
     * 手撕dp 100% 40% 时间空间:m*n
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //1 定义dp： 一个m*n的二维矩阵，dp[m][n]代表 到达mn有多少种路径
        //2 转移方程式：dp[m][n] = dp[m][n-1] + dp[m-1][n] 遇上石头跳过
        //3 初始化：dp[0][0] = 0，横轴[0][*]和纵轴[*][0]都是1，遇上石头停止
        //4 遍历顺序：横向，一层一层遍历
        //5 print

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for(int i=0;i<dp.length; i++){
            if(obstacleGrid[i][0]==1) break;
            dp[i][0]=1;
        }
        for(int i=0;i<dp[0].length; i++){
            if(obstacleGrid[0][i]==1) break;
            dp[0][i]=1;
        }

        //遍历dp
        for(int row=1; row<dp.length; row++){
            for(int col=1; col<dp[0].length; col++){
                if(obstacleGrid[row][col] == 1) continue;
                dp[row][col] = dp[row-1][col] + dp[row][col-1];
            }
        }

        return dp[dp.length-1][dp[0].length-1];
    }
}
