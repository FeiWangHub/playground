package com.fei.playground.algorithm.leetcode;

import java.util.Stack;

/**
 * 判断字符串左右括号是否匹配
 * https://leetcode-cn.com/problems/valid-parentheses/
 * 解题思路  https://github.com/youngyangyang04/leetcode-master/blob/master/problems/0020.有效的括号.md
 */
public class E_Stack_ValidBracket {

    public boolean isValid(String s) {
        Stack<Character> deque = new Stack<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                deque.push(')');
            }else if (ch == '{') {
                deque.push('}');
            }else if (ch == '[') {
                deque.push(']');
            } else if (deque.isEmpty() || deque.peek() != ch) {
                return false;
            }else {//如果是右括号判断是否和栈顶元素匹配
                deque.pop();
            }
        }
        //最后判断栈中元素是否匹配
        return deque.isEmpty();
    }

}
