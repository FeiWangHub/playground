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

    /**
     * 官方2 遍历计数 TODO
     */
    public int numSteps(String s) {
        int n = s.length();
        int ans = 0;
        // meet1 记录我们有没有遇见过字符 1
        boolean meet1 = false;
        // 从后向前遍历字符
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                // 如果当前字符为 0，分为两种情况
                // (1) 还没有遇见过字符 1，那么这个 0 是字符串低位的 0，需要一次除二操作
                // (2) 遇见过字符 1，那么这个 0 会因为它右侧的某次加一操作变为 1，因此它需要一次加一和一次除二操作
                ans += (meet1 ? 2 : 1);
            } else {
                // 如果当前字符为 1，分为两种情况
                // (1) 还没有遇见过字符 1，那么这个 1 需要一次加一和一次除二操作
                //     这里需要考虑一种特殊情况，就是这个 1 是字符串最左侧的 1，它并不需要任何操作
                // (2) 遇见过字符 1，那么这个 1 会因为它右侧的某次加一操作变为 0，因此它只需要一次除二操作
                if (!meet1) {
                    if (i != 0) {
                        ans += 2;
                    }
                    meet1 = true;
                } else {
                    ++ans;
                }
            }
        }
        return ans;
    }

}
