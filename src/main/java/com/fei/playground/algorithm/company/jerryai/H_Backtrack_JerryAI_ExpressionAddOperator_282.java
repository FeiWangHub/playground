package com.fei.playground.algorithm.company.jerryai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 一亩三分地面经 https://www.1point3acres.com/bbs/thread-859737-1-1.html
 * 题目是给一串数字（0-9）每个数字之间可以加+-号或者不加组成的表达式计算结果等于给定的目标数输出所有满足条件的表达式
 * 例如： [1 2 3 4 5 6 7 8 9]目标 100
 * 可能的组合：
 * 1 + 23 - 4 + 56 + 7 + 8 + 9
 * -1 - 2 + 34 - 5 -xxxx
 */
public class H_Backtrack_JerryAI_ExpressionAddOperator_282 {

    /**
     * jerry简化版
     * 题目是给一串数字（0 - 9）每个数字之间可以加 + - 号或者不加，组成的表达式计算结果等于 给定的目标数，输出所有满足条件的表达式。
     * 例如： [1 2 3 4 5 6 7 8 9]  目标 100
     * 可能的组合：
     * 1 + 23 - 4 + 56 + 7 + 8 + 9
     * -1 - 2 + 34 - 5 - 6 + 78 + 9
     */
    List<String> res = new LinkedList<>();
    //StringBuilder curPath = new StringBuilder();
    char[] numChars;

    public List<String> addOperators_jerry(String num, int target) {
        numChars = num.toCharArray();
        backtrack_jerry(0, target, "");
        return res;
    }

    public void backtrack_jerry(int curIdx, int remain, String path) {
        if (curIdx == numChars.length && remain == 0) {
            res.add(path);
            return;
        }

        for (int i = curIdx; i < numChars.length; i++) {
            //尝试在不同的位置，组合数字，然后加减剩下的
            char[] chars = Arrays.copyOfRange(numChars, curIdx, i + 1);
            String curNum = new String(chars);
            backtrack_jerry(i + 1, remain + Integer.parseInt(curNum), path + '-' + curNum);
            backtrack_jerry(i + 1, remain - Integer.parseInt(curNum), path + '+' + curNum);
        }
    }

    public static void main(String[] args) {
        H_Backtrack_JerryAI_ExpressionAddOperator_282 t = new H_Backtrack_JerryAI_ExpressionAddOperator_282();
        t.addOperators_jerry("123456789", 100).forEach(System.out::println);
    }

// 上边版本的python版
//    # def generate_formula(nums, target):
//            #
//            #     def dfs(nums, remain, path):
//            #       if not nums:
//            #         if remain == 0:
//            #           res.append(path)
//            #         return
//            #       for i in range(len(nums)):
//            #         cur = int(''.join(nums[:i+1]))
//            #         print cur
//#         dfs(nums[i+1:], remain+cur, path+'-'+str(cur))
//            #         dfs(nums[i+1:], remain-cur, path+'+'+str(cur))
//            #
//            #     nums = [str(n) for n in nums]
//            #     res = []
//            #     dfs(nums, target, '')
//#     return res
//#
//        # print generate_formula([1,2,3,4,5,6,7,8,9], 100);

    /**
     * Leetcode 282 官方版 + - * 三种
     * 给定一个仅包含数字0-9的字符串 num 和一个目标值整数 target，在 num 的数字之间添加 二元 运算符（不是一元）+、-或*，
     * 返回 所有 能够得到 target 的表达式。
     * 注意，返回表达式中的操作数 不应该 包含前导零。
     * https://leetcode.cn/problems/expression-add-operators/
     */
    int n;
    String num;
    int target;
    List<String> ans;

    public List<String> addOperators(String num, int target) {
        this.n = num.length();
        this.num = num;
        this.target = target;
        this.ans = new ArrayList<String>();
        StringBuffer expr = new StringBuffer();
        backtrack(expr, 0, 0, 0);
        return ans;
    }

    public void backtrack(StringBuffer expr, int i, long res, long mul) {
        if (i == n) {
            if (res == target) {
                ans.add(expr.toString());
            }
            return;
        }
        int signIndex = expr.length();
        if (i > 0) {
            expr.append(0); // 占位，下面填充符号
        }
        long val = 0;
        // 枚举截取的数字长度（取多少位），注意数字可以是单个 0 但不能有前导零
        for (int j = i; j < n && (j == i || num.charAt(i) != '0'); ++j) {
            val = val * 10 + num.charAt(j) - '0';
            expr.append(num.charAt(j));
            if (i == 0) { // 表达式开头不能添加符号
                backtrack(expr, j + 1, val, val);
            } else { // 枚举符号
                expr.setCharAt(signIndex, '+');
                backtrack(expr, j + 1, res + val, val);
                expr.setCharAt(signIndex, '-');
                backtrack(expr, j + 1, res - val, -val);
                expr.setCharAt(signIndex, '*');
                backtrack(expr, j + 1, res - mul + mul * val, mul * val);
            }
        }
        expr.setLength(signIndex);
    }

}
