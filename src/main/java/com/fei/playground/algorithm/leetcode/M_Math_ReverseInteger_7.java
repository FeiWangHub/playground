package com.fei.playground.algorithm.leetcode;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−231, 231− 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * https://leetcode.cn/problems/reverse-integer/
 */
public class M_Math_ReverseInteger_7 {

    /**
     * 官方数学100% 45%
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }

    /**
     * 评论的数学%法 100% 32%
     * 缺点是用了long
     */
    public int reverse_long(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (int) n == n ? (int) n : 0;
    }

    /**
     * 手撕翻转优化 40% 5%
     */
    public int reverse_mine(int x) {
        boolean isNegative = x < 0;
        String str = Integer.toString(x);

        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        //去除头部的0
        for (char c : chars) {
            if (c == '-') continue;//暂时移除符号
            sb.append(c);
        }
        sb.reverse();

        if (isOverSize(sb)) return 0;
        //处理符号
        if (isNegative) sb.insert(0, '-');
        //处理大小
        return Integer.parseInt(sb.toString());
    }

    public boolean isOverSize(StringBuilder sb) {
        if (sb.length() > 10) return true;
        if (sb.length() < 10) return false;
        String max = "2147483647";
        char m = ' ';
        char s = ' ';
        for (int i = 0; i < 10; i++) {
            m = max.charAt(i);
            s = sb.charAt(i);
            if (m == s) continue;
            return s > m;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new M_Math_ReverseInteger_7().isOverSize(new StringBuilder("9646324351")));
    }

}
