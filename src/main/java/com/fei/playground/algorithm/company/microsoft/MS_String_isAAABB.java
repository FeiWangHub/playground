package com.fei.playground.algorithm.company.microsoft;

/**
 * Write a function solution that, given a string S consisting of N letters 'a'
 * and/or "b' returns true when all occurrences of letter 'a' are before all
 * occurrences of letter "b' and returns false otherwise.
 * Examples:
 * 1. Given S = "aabbb", the function should return true.
 * 2. Given S = "ba", the function should return false.
 * 3. Given S = "aaa", the function should return true. Note that 'b' does not need
 * to occur in S.
 * 4. Given S = "b", the function should return true. Note that 'a' does not need to
 * occur in S.
 * 5. Given S = "abba", the function should return false.
 */
public class MS_String_isAAABB {

    public boolean solution(String S) {
        boolean result = true;
        boolean foundFirstB = false;

        for(int i=0; i<S.length(); i++){
            if(S.charAt (i) =='a' ){
                if (foundFirstB){
                    return false;
                }else{
                    continue;
                }
            }else{
                foundFirstB = true;
            }
        }
        return result;
    }

}
