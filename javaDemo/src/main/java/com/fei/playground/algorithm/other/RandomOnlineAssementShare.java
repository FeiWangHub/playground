package com.fei.playground.algorithm.other;

import java.util.Stack;

public class RandomOnlineAssementShare {

    /**
     * 折扣码判断 isStringValid 2021-7-9
     * OA一道新题。 判断一个string是否Valid。条件1：在一个valid的string头尾加相同字母， 比如 AA 是valid 那么 BAAB就是valid。空是valid。
     * 条件2：两个valid的string的concatenation是valid，比如AA和BB是valid，那么AABB就是valid。
     * 例子: EABBACDDFFCE
     * BB是Valid所以ABBA是valid，DD和FF是valid所以DDFF是valid，所以CDDFFC是valid 所以ABBACDDFFC是valid，所以EABBACDD
     */
    public boolean isValid(String s) {
        Stack<Character> unPaired = new Stack<>();
        for (char c : s.toCharArray()) {
            //if (!unPaired.isEmpty() && unPaired.peek() == c)
            if (unPaired.peek() == c) {
                unPaired.pop();
            } else {
                unPaired.add(c);
            }
        }
        return unPaired.isEmpty();
    }

    /**
     * DP 给程序员分组，每组人数给定，每组的最大最小能力值差不能超过给定的上限，问最多能分出多少组
     * 3个输入：
     * 数组，每个元素代表一个程序员的能力值
     * 整数，代表每组人数
     * 整数，代表每组的最大最小能力值的上限
     * 1个输出：
     * 整数，代表最多能分出多少组
     */

}
