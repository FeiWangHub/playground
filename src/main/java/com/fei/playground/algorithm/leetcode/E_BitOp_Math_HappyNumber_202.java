package com.fei.playground.algorithm.leetcode;

import java.util.HashSet;

/**
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 */
public class E_BitOp_Math_HappyNumber_202 {

    /**
     * 手撕优化 80% 76%
     * 用取模每次获取最后一位数
     */
    public boolean isHappy(int n) {
        //1. 最终会得到 11。
        //2. 最终会进入循环。
        HashSet<Integer> history = new HashSet<>();
        int sum = 0;
        while (true) {
            sum = getNext(n);
            if (sum == 1) return true;
            if (history.contains(sum)) return false;
            history.add(sum);
            n = sum;
        }
    }

    public int getNext(int n) {
        int sum = 0;
        while (n != 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;
        }
        return sum;
    }

    /**
     * 我的 9.2% 5%
     */
    public boolean isHappy_mine(int n) {
        //1. 最终会得到 11。
        //2. 最终会进入循环。
        HashSet<Integer> history = new HashSet<>();
        while (true) {
            char[] chars = Integer.toString(n).toCharArray();
            int sum = 0;
            for (char c : chars) {
                sum += Math.pow(Integer.parseInt(Character.toString(c)), 2);
            }
            if (sum == 1) return true;
            if (history.contains(sum)) return false;
            history.add(sum);
            n = sum;
        }
    }

}
