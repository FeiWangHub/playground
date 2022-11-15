package com.fei.playground.algorithm.leetcode;

/**
 * 231. 2 的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得n == 2x ，则认为 n 是 2 的幂次方。
 */
public class E_BitOp_isPowerOfTwo_231 {

    //查1个个数法
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return Integer.bitCount(n) == 1;
    }

    //移除最右边的1法
    public boolean isPowerOfTwo_remove0(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    int BIG = 1 << 30;

    //判断是否是2的30次方的约数
    public boolean isPowerOfTwo_modOfLargeInt(int n) {
        return n > 0 && BIG % n == 0;
    }

}
