package com.fei.playground.algorithm.hackerRank.M_McKinsey_VA;

/**
 * Highly Profitable Months
 * https://www.chegg.com/homework-help/questions-and-answers/solve-java-starter-code-import-javaio-import-javamath-import-javasecurity-import-javatext--q83029294
 * <p>
 * For an analysis parameter k, a group of k consecutive months is said to be
 * highly profitable if the values of the stock prices are strictly increasing for
 * those months. Given the stock prices of the company for n months and the
 * analysis parameter k, find the number of highly profitable months.
 * Example
 * stockprices = [5, 3, 5, 7, 8]
 * k=3
 * Hence the answer is 2.
 */
public class M_HighlyProfitableMonths {

    public static int findMonths(int[] prices, int k) {
        //双指针，保证left和right之间是一个asc数组
        int left = 0, right = 0, count = 0, len = prices.length;
        int tempLen = 0;
        while (right < len && left <= len - k) {
            if (right != 0 && prices[right - 1] > prices[right]) {
                left = right;
            }
            tempLen = right - left + 1;
            //1 Not enough K
            if (tempLen < k) {
                right++;

            }
            //2 Match
            else if (tempLen == k) {
                count++;
                left++;
                right++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] input = {5, 3, 5, 7, 8}; // 2
        System.out.println(findMonths(input, 3));
    }

}
