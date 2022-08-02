package com.fei.playground.algorithm.leetcode;

import java.util.HashMap;

/**
 * 罗马数字转整数
 * https://leetcode.cn/problems/roman-to-integer/
 * <p>
 * 罗马数字包含以下七种字符:I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1 。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。
 */
public class E_RomanToInt_13 {

    //我的解法
    public int romanToInt(String s) {
        HashMap<Character, Integer> key2int = new HashMap<>(7);
        key2int.put('I', 1);
        key2int.put('V', 5);
        key2int.put('X', 10);
        key2int.put('L', 50);
        key2int.put('C', 100);
        key2int.put('D', 500);
        key2int.put('M', 1000);

        int sum = 0;

        //当前的数字是加是减，取决于下一个数字是否<=本数字
        for (int i = 0; i < s.length(); i++) {
            Character cur = s.charAt(i);
            int curVal = key2int.get(cur);
            if (i == s.length() - 1) {//last one
                sum += curVal;
                return sum;
            }

            int nextVal = key2int.get(s.charAt(i + 1));
            if (nextVal > curVal) {
                sum -= curVal;
            } else {
                sum += curVal;
            }
        }

        return sum;
    }

    //暴力破解 击败cpu100% mem88%
    public static int romanToInt2(String s) {
        int sum = 0;
        int last = 1000;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    sum += 1;
                    last = 1;
                    break;
                case 'V':
                    if (last < 5) {
                        sum += 5 - last * 2;
                    } else {
                        sum += 5;
                    }
                    last = 5;
                    break;
                case 'X':
                    if (last < 10) {
                        sum += 10 - last * 2;
                    } else {
                        sum += 10;
                    }
                    last = 10;
                    break;
                case 'L':
                    if (last < 50) {
                        sum += 50 - last * 2;
                    } else {
                        sum += 50;
                    }
                    last = 50;
                    break;
                case 'C':
                    if (last < 100) {
                        sum += 100 - last * 2;
                    } else {
                        sum += 100;
                    }
                    last = 100;
                    break;
                case 'D':
                    if (last < 500) {
                        sum += 500 - last * 2;
                    } else {
                        sum += 500;
                    }
                    last = 500;
                    break;
                case 'M':
                    if (last < 1000) {
                        sum += 1000 - last * 2;
                        last = 1000;
                    } else {
                        sum += 1000;
                    }
                    break;
                default:
            }
        }
        return sum;
    }

}
