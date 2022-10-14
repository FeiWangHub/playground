package com.fei.playground.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 你可以假设给定的表达式总是有效的。所有中间结果将在[-231, 231- 1] 的范围内。
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * 输入：s = "3+2*2"
 * 输出：7
 * <p>
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 * https://leetcode.cn/problems/basic-calculator-ii/
 */
public class M_Stack_CalculatorII_4Ops_227 {

    /**
     * 官方 69% 91%
     */
    public int calculate_official(String s) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    /**
     * 手撕 35% 23% 之用一个数字栈
     */
    public static int calculate(String s) {
        Stack<Integer> digits = new Stack<>();
        digits.push(0);
        char preSign = '+';
        int curNum = 0;
        char cur;

        for (int i = 0; i < s.length(); i++) {
            cur = s.charAt(i);
            switch (cur) {
                case '+':
                case '-':
                case '/':
                case '*':
                    cal(digits, preSign, curNum);//结算上一个数字和上一个op操作
                    curNum = 0;
                    preSign = cur;
                    break;
                case ' ':
                    break;
                default://digit
                    curNum = curNum * 10 + cur - '0';
                    //curDigit.append(cur);
                    break;
            }
        }
        cal(digits, preSign, curNum);

        int sum = 0;
        while (!digits.isEmpty()) {
            sum += digits.pop();
        }

        return sum;
    }

    private static void cal(Stack<Integer> digits, char preSign, int curInt) {
        //System.out.println("遇上了符号，当前数字为:" + curInt + "当前stack为: " + digits);
        switch (preSign) {
            case '+':
                digits.push(curInt);
                break;
            case '-':
                digits.push(-curInt);
                break;
            case '*':
                digits.push(digits.pop() * curInt);
                break;
            case '/':
                digits.push(digits.pop() / curInt);
                break;
        }
    }

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
//        System.out.println(calculate("42"));
    }
}
