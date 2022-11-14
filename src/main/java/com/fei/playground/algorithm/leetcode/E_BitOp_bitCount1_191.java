package com.fei.playground.algorithm.leetcode;

/**
 * 191. 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 */
public class E_BitOp_bitCount1_191 {

    /**
     * 官方位运算优化 100% 96%
     */
    public int hammingWeight_bitOp2(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

    /**
     * 官方位运算 循环检查二进制位
     * 我们可以直接循环检查给定整数 n 的二进制位的每一位是否为1。
     * 代码中，检查第i位时，让n与2的i次方做与运算；仅当n的第i位为1时，运算结果不为0。
     * (1 << i 代表数字1的二进制左移i位，也就是2的i次方)
     */
    public int hammingWeight_bitOp1(int n) {
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                ret++;
            }
        }
        return ret;
    }

    /**
     * 手撕 toUnsignedString 10% 61%
     */
    public int hammingWeight_mine(int n) {
        String s = Integer.toUnsignedString(n, 2);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') res++;
        }
        return res;
    }

    /**
     * 100% 80%
     */
    public int hammingWeight_jdk(int n) {
        return Integer.bitCount(n);
    }

}
