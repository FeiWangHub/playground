package com.fei.playground.algorithm.company;

import com.fei.playground.util.DateUtil;

import java.util.Date;
import java.util.Stack;

/**
 * 运行字符串中的四元表达式
 */
public class MX_5th_StringCalculator {

    public static void main(String[] args) {
        System.out.println(String.format("---- Started, time now is %s ----", DateUtil.HHmmssSSS.format(new Date())));
        //min Abs(sum) -6, -2, 8    5  10
        int[] arr = {-10, -5, 0, 1, 2, 4, 8, 18};
        int[] arr2 = {0, 1, 2, 4, 8, 18};

        cal("1+1+1");
    }

    //input String, 4-2*5/4+1  : +-*/
    public static void cal(String input) {
        Stack<Float> digitStack = new Stack<>();
        Stack<Boolean> opStack = new Stack<>();//true for +, false for -

        float lastDigit, nextDigit;
        for (int i = 0; i < input.length(); i++) {
            char cur = input.charAt(i);
            //+
            if (cur == '+') {
                opStack.push(true);
            } else if (cur == '-') {
                opStack.push(false);
            } else if (cur == '*') {
                lastDigit = digitStack.pop();
                nextDigit = input.charAt(i + 1);
                digitStack.push(lastDigit * nextDigit);
                i++;
            } else if (cur == '/') {
                lastDigit = digitStack.pop();
                nextDigit = input.charAt(i + 1);
                digitStack.push(lastDigit / nextDigit);
                i++;
            } else {
                digitStack.push(Float.parseFloat(input.substring(i, i + 1)));
            }
        }

        float result = 0;
        while(!opStack.isEmpty()){
            if(opStack.pop()){//+
                result += digitStack.pop();
            }else{//-
                result -= digitStack.pop();
            }
        }
        result += digitStack.pop();
        System.out.println(result);
    }

}
