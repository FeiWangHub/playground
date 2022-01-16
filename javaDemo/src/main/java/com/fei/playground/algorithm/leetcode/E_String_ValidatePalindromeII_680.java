package com.fei.playground.algorithm.leetcode;

/**
 * https://leetcode-cn.com/problems/valid-palindrome-ii/
 */
public class E_String_ValidatePalindromeII_680 {

    /**
     * 我的第一版写法，461 / 467，bug是当遇上不同的值时，也许删左边和删右边都可以，但是左边删除后往下进行就能valid，删右边就不valid了
     */
    public static boolean validPalindrome_mine(String s) {
        //双指针 从左右两边向中间靠拢
        //如果遇上不同的，看左或右的下一个能不能match，能pass，不能fail
        //再遇上不同的，fail

        boolean passUsed = false;
        int left = 0, right = s.length()-1;

        while(left < right){
            if(s.charAt(left) == s.charAt(right)){
                left++;
                right--;
            }else if(!passUsed){
                if(s.charAt(left+1)==s.charAt(right)){//
                    left+=2;
                    right--;
                    passUsed = true;
                }else if(s.charAt(right-1)==s.charAt(left)){
                    right-=2;
                    left++;
                    passUsed = true;
                }else{
                    System.out.println(String.format("left: %d, right: %d",left, right));
                    System.out.println(String.format("leftStr: %C, rightStr: %C", s.charAt(left), s.charAt(right)));
                    return false;
                }
            }else{
                System.out.println(String.format("left: %d, right: %d",left, right));
                System.out.println(String.format("leftStr: %C, rightStr: %C", s.charAt(left), s.charAt(right)));
                return false;
            }
        }

        return true;
    }

    /**
     * 碰上不同的，判断subString是不是回文 94 72%
     */
    public static boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                ++low;
                --high;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    public static boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String input = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        System.out.println(input);
        String reverse = new StringBuffer(input).reverse().toString();
        System.out.println(reverse);
        System.out.println("012345678901234567890123456789");
        System.out.println(validPalindrome(input));
    }

}
