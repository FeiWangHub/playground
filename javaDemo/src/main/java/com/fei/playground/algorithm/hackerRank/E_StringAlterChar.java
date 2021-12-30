package com.fei.playground.algorithm.hackerRank;

/**
 * https://www.hackerrank.com/challenges/alternating-characters/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=strings
 */
public class E_StringAlterChar {

    public static int alternatingCharacters(String s) {
        // Write your code here
        char[] arr = s.toCharArray();
        char last =' ';
        int count=0;
        for(char c:arr){
            if(c==last){
                count++;
            }else{
                last=c;
            }
        }
        return count;
    }

}
