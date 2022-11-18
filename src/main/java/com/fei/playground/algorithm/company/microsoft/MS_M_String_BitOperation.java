package com.fei.playground.algorithm.company.microsoft;

import java.math.BigInteger;

/**
 * TODO
 * You are given a string S of length N which encodes a non-negative number V
 * in a binary form. Two types of operations may be performed on it to modify its
 * value:
 * if V is odd, subtract 1 from it;
 * • if V is even, divide it by 2.
 * These operations are performed until the value of V becomes 0.
 * For example, if string S = "011100", its value V initially is 28. The value of V
 * would change as follows:
 * V= 28, which is even: divide by 2 to obtain 14;
 * V = 14, which is even: divide bv 2 to obtain 7:
 * V= 7, which is odd: subtract 1 to obtain 6;
 * V= 6, which is even: divide by 2 to obtain 3;
 * V= 3, which is odd: subtract 1 to obtain 2;
 * V= 2, which is even: divide by 2 to obtain 1;
 * V= 1, which is odd: subtract 1 to obtain 0.
 * Seven operations were required to reduce the value of V to 0
 *
 * 类似 https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 */
public class MS_M_String_BitOperation {

    //字符串操作解法
    //判断偶数: 尾字符是否是1
    //加1：最低位0变1，然后看需求递进carry
    //减1：拿掉最右边的一个1，然后剩下的0变为1
    //除以2：右移动，拿掉最后一位数字
    //乘以2：左移动，末尾加上0



    //BIG DECIMAL解法 击败12.77 5.68%
    private static final int BASIC_RADIX = 2;
    private static final String NUM2_STR = "2";
    private static final String ONE_STR = "1";
    private static final String ZERO_STR = "0";

    public int numSteps_BigInt(String s) {
        int len = s.length();
        int count = 0;
        if (len == 1 && s.charAt(0) == '1') {
            return count;
        }
        // 转成大整数运算
        BigInteger base = new BigInteger(s, BASIC_RADIX);
        BigInteger zero = new BigInteger(ZERO_STR);
        BigInteger one = new BigInteger(ONE_STR);
        BigInteger two = new BigInteger(NUM2_STR);
        // 迭代计算直到满足出口条件
        while (base.compareTo(one) != 0) {
            if(base.mod(two).compareTo(zero) == 0) {
                base = base.divide(two);
            } else {
                base = base.subtract(one);
            }
            count ++;
        }
        return count;
    }

}
