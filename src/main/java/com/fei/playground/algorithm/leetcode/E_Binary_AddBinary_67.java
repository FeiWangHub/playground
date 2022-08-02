package com.fei.playground.algorithm.leetcode;

/**
 * 二进制求和
 * https://leetcode.cn/problems/add-binary/submissions/
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 */
public class E_Binary_AddBinary_67 {

    /**
     * 99% 55.94%
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int lenA = a.length();
        int lenB = b.length();
        int maxLen = Math.max(lenA, lenB);
        char charA = ' ', charB = ' ';
        int carry = 0;

        for (int i = 0; i < maxLen; i++) {
            charA = (lenA - 1) >= i ? a.charAt(lenA - 1 - i) : ' ';
            charB = (lenB - 1) >= i ? b.charAt(lenB - 1 - i) : ' ';

            if (charA == '1' && charB == '1') { //sum 2
                if (carry == 1) {
                    sb.append('1');
                } else {
                    sb.append('0');
                    carry = 1;
                }
            } else if ((charA == '1' && charB == '0')
                    || (charA == '0' && charB == '1')
                    || (charA == '1' && charB == ' ')
                    || (charA == ' ' && charB == '1')) { // sum 1
                if (carry == 1) {
                    sb.append('0');
                } else {
                    sb.append('1');
                }
            } else { //sum 0
                if (carry == 1) {
                    sb.append('1');
                    carry = 0;
                } else {
                    sb.append('0');
                }
            }
        }

        if (carry != 0) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

    /**
     * parse int
     */
    public String addBinaryParseInt(String a, String b) {
        return Integer.toBinaryString(
                Integer.parseInt(a, 2) + Integer.parseInt(b, 2)
        );
    }

    /**
     * official mock 代码量少多了
     * 99% 44.16%
     */
    public String addBinaryMock(String a, String b) {
        StringBuffer ans = new StringBuffer();

        int n = Math.max(a.length(), b.length()), carry = 0;
        for (int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char) (carry % 2 + '0'));
            carry /= 2;
        }

        if (carry > 0) {
            ans.append('1');
        }
        ans.reverse();
        return ans.toString();
    }

    public static void main(String[] args) {
        int a = '1' - '0';
        System.out.println('1' - '0');
        System.out.println('a' - 'b');
    }

}
