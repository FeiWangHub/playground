package com.fei.playground.algorithm.hackerRank;

import java.util.Arrays;

/**
 ** You can perform the following operations on the string, :
 * Capitalize zero or more of 's lowercase letters.
 * Delete all of the remaining lowercase letters in .
 *
 * a: the string to modify
 * b: the string to match
 * @return YES OR NO
 * https://www.hackerrank.com/challenges/abbr/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dynamic-programming
 */
public class M_DP_Abbrevation {

    /**
     * TODO 此题的完美解法还未自己 我自己只能递归
     * 也许的解读：
     * i是输入长度 j是预期目标长度
     * dp[i][j] = 1 表示：a的前i个字母能通过操作，变为b的前j个字母
     * dp[i][j] = 0 表示：  .........不能.....................
     * b是目标字符 a是输入字符
     */
    static String abbreviation(String a, String b) {
        boolean[][] dp = new boolean[b.length() + 1][a.length() + 1];
        dp[0][0] = true;
        for (int j = 1; j < dp[0].length; j++) {
            if (Character.isLowerCase(a.charAt(j - 1))) dp[0][j] = dp[0][j - 1];
        }
        for (int i = 1; i < dp.length; i++) {//循环目标字符
            for (int j = 1; j < dp[0].length; j++) {//循环输入字符
                char ca = a.charAt(j - 1), cb = b.charAt(i - 1);
                if (ca >= 'A' && ca <= 'Z') {//如果是大写且相等,可操作,状态与上次相等
                    if (ca == cb) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }else {//如果小写
                    ca = Character.toUpperCase(ca);
                    if (ca == cb) dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1];//如果大写后相等，可操作，它的状态等于跟上个字母一样，或者
                    else dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[b.length()][a.length()] ? "YES" : "NO";
    }

    /**
     * 9/16 test cases failed
     * 思路不对，应该循环a，而不是循环b
     */
    public static String abbreviation_1st(String a, String b) {
        // Write your code here
        char[] expected = b.toCharArray();
        char[] input = a.toCharArray();
        int curIdx = 0;
        boolean deleteFlag = false;

        for(int i=0; i<expected.length; i++){
            if(curIdx==input.length){
                return "NO";
            }
            char target = expected[i];
            char cur = input[curIdx];

            //match
            if(cur==target){
                curIdx++;
                continue;
            }

            if(Character.toUpperCase(cur)==target){
                // match | toUpper|
                if(deleteFlag){//reset check
                    i=-1;
                    deleteFlag=false;
                    continue;
                }else{
                    curIdx++;
                    continue;
                }
            }else if(Character.isLowerCase(cur)){
                // removeLower
                curIdx++;
                i--;
                deleteFlag=true;
                continue;
            }else{
                //| fail
                return "NO";
            }
        }

        //check if there remaining capital
        if(curIdx != input.length){
            for(int i=curIdx; i<input.length; i++){
                if (Character.isUpperCase(input[i])){
                    char[] newInput = Arrays.copyOfRange(input, i, input.length);
                    System.out.println("需要重新检查的尾部字符串为"+new String(newInput));
                    return abbreviation_1st(new String(newInput), b);
                }
            }
        }

        return "YES";
    }

    public static void main(String[] args) {
//        System.out.println(abbreviation("KXzQ", "K"));
//        System.out.println(abbreviation_1st("beFgH", "EFG"))
    }

}
