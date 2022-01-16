package com.fei.playground.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：
 *
 * answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
 * answer[i] == "Fizz" 如果 i 是 3 的倍数。
 * answer[i] == "Buzz" 如果 i 是 5 的倍数。
 * answer[i] == i （以字符串形式）如果上述条件全不满足。
 * https://leetcode-cn.com/problems/fizz-buzz/
 */
public class E_Array_FizzBuzz_412 {

    public List<String> fizzBuzz(int n) {
        LinkedList<String> r = new LinkedList<>();
        for(int i=1; i<=n; i++){
            if(i % 3 == 0){
                if (i % 5 ==0){
                    r.add("FizzBuzz");
                }else{
                    r.add("Fizz");
                }
            }else if (i % 5 ==0){
                r.add("Buzz");
            }else{
                r.add(Integer.toString(i));
            }
        }
        return r;
    }

}
