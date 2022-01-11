package com.fei.playground.algorithm.codility;

import java.util.HashSet;

/**
 * Demo测试 找到最小未出现的Int
 */
public class E_minUnoccuredInt {

    public int solution(int[] A) {
        // write your code in Java SE 8
        Integer min = null;
        Integer max = null;
        HashSet<Integer> set = new HashSet<>(A.length);

        for(int num:A){
            if(max == null || num > max) max = num;
            if(min == null || num < min) min = num;
            set.add(num);
        }

        int target = 1;
        while(target <= max){
            if(set.contains(target)){
                target++;
            }else{
                return target;
            }
        }

        return target;
    }
}
