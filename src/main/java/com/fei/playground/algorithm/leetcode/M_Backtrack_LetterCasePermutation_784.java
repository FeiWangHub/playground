package com.fei.playground.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 784. 字母大小写全排列
 * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
 * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输出。
 */
public class M_Backtrack_LetterCasePermutation_784 {

    List<String> res = new LinkedList<>();
    char[] arr;

    /**
     * 手撕回溯 100% 5% 时间2的n次方 空间n
     * 每个位置有3中情况，大写，小写，数字跳过
     */
    public List<String> letterCasePermutation(String s) {
        this.arr = s.toCharArray();
        backtrack(0);
        return res;
    }

    public void backtrack(int curIdx) {
        if (curIdx == arr.length) {//end
            res.add(new String(arr));
            return;
        }

        if (Character.isDigit(arr[curIdx])) {
            backtrack(curIdx + 1);
        } else {
            //小写
            arr[curIdx] = Character.toLowerCase(arr[curIdx]);
            backtrack(curIdx + 1);
            //大写
            arr[curIdx] = Character.toUpperCase(arr[curIdx]);
            backtrack(curIdx + 1);
        }
    }

}
