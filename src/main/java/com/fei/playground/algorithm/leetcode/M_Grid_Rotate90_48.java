package com.fei.playground.algorithm.leetcode;

/**
 * 一个二维matrix，顺时针旋转90度
 * (评论解法思路 一张图先上下反转，再对角反转)
 * https://leetcode-cn.com/problems/rotate-image/
 */
public class M_Grid_Rotate90_48 {

    /**
     * 我花了几乎3个小时的解法 100% 100%
     * 规律 对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置
     * 公式 m[row][col] -> m[col][n-row-1]
     */
    public void rotate(int[][] matrix) {
        //由外而内顺指针循环
        final int len = matrix.length;
        if(len==0) return;
        final int total = len * len;

        int count = 0;
        int circle = 1;//第一圈
        int x=0, y=0;

        while(count != total){
            if(count + 1 == total) return;
            while(y < len - circle){
                //每个元素四次swap
                //y=x; x=lenX-circle-1
                swap(matrix, x, y, y, len-x-1);
                swap(matrix, x, y, len-x-1, len-y-1);
                swap(matrix, x, y, len-y-1, x);
                count+=4;
                y++;//下一个元素
            }
            //下一圈
            circle++;
            x = circle-1;
            y = circle-1;
        }
    }

    public void swap(int[][] matrix, int x1, int y1, int x2, int y2){
        int tmp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = tmp;
    }

    /**
     * 官方暴力解法1
     * 规律 对于矩阵中第 i 行的第 j 个元素，在旋转后，它出现在倒数第 i 列的第 j 个位置。
     */
    public void rotate_brute(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix_new = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix_new[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                matrix[i][j] = matrix_new[i][j];
            }
        }
    }

    /**
     * 官方原地旋转法2
     */
    public void rotate_official(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

}
