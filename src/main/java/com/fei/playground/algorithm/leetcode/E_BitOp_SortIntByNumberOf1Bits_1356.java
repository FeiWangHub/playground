package com.fei.playground.algorithm.leetcode;

import java.util.*;

/**
 * 1356. 根据数字二进制下 1 的数目排序
 * 给你一个整数数组arr。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中1的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 * https://leetcode.cn/problems/sort-integers-by-the-number-of-1-bits/
 */
public class E_BitOp_SortIntByNumberOf1Bits_1356 {

    /**
     * 民间高手解法
     * Integer.bitCount(arr[i]) 直接拿到1的个数
     */
    public int[] sortByBits_best(int[] arr) {
        int[] map = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map[i] = Integer.bitCount(arr[i]) * 10000000 + arr[i];
        }
        Arrays.sort(map);
        for (int i = 0; i < map.length; i++) {
            map[i] = map[i] % 10000000;
        }
        return map;
    }

    /**
     * 官方 排序法 59% 10%
     * 核心是getNumberOf1Bits函数
     */
    public int[] sortByBits(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
            bit[x] = getNumberOf1Bits(x);
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    /**
     * 获得int x的二进制有多少个1
     */
    public int getNumberOf1Bits(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 2;
            x /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        E_BitOp_SortIntByNumberOf1Bits_1356 t = new E_BitOp_SortIntByNumberOf1Bits_1356();
        System.out.println(t.getNumberOf1Bits(14));
    }

}
