package com.fei.playground.algorithm.hackerRank;

/**
 * Given n, return the nth number in the Fibonacci  sequence.
 * https://www.hackerrank.com/challenges/ctci-fibonacci-numbers/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=recursion-backtracking
 */
public class E_Recursion_Fibonacci {

    public static int fibonacci(int n) {
        // Complete the function.
        if(n==0) return 0;
        if(n==1) return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }

}
