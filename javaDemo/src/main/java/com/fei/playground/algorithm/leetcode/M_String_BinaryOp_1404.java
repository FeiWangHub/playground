package com.fei.playground.algorithm.leetcode;

import java.math.BigInteger;

/**
 * 将二进制表示减到 1 的步骤数
 * 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：
 * 如果当前数字为偶数，则将其除以 2 。
 * 如果当前数字为奇数，则将其加上 1 。
 * 题目保证你总是可以按上述规则将测试用例变为 1 。
 * https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 */
public class M_String_BinaryOp_1404 {

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
                base = base.add(one);
            }
            count ++;
        }
        return count;
    }

}
