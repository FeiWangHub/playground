package com.fei.playground.algorithm.leetcode;

/**
 * 位运算符
 * << (左移)空位补0，被移除的最高位丢弃；
 * >> (右移)如果被移位的最高位是0，右移后，空缺位补0；如果最高位是1，最高位补1；
 * >>> 右移后，无论最高位是0还是1，空缺位都用0补；
 * & 任何二进制位和0进行&运算，结果都是0；和1进行&都是原值
 * | (逻辑或)任何二进制位和0进行|运算，结构都是原值；和1进行|运算结果是1；
 * ^ (异或)任何相同二进制进行^运算，结果是0；不同二进制位^运算结果是1
 */
public class E_BitOp_Utils {

    /**
     * 1 << i 代表数字1的二进制左移i位，也就是2的i次方
     */
    public static int twoToPowerI(int i) {
        return 1 << i;
    }

    /**
     * 转换为2进制后，移除掉最后边的1
     *
     * @param n 正整数
     */
    public static int removeTailingOne(int n) {
        return n & n - 1;
    }

}
