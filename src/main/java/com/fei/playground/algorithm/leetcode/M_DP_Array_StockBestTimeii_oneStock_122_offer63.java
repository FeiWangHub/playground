package com.fei.playground.algorithm.leetcode;

/**
 * 122. 买卖股票的最佳时机 II
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class M_DP_Array_StockBestTimeii_oneStock_122_offer63 {

    /**
     * 见好就卖 100% 6%
     */
    public int maxProfit(int[] prices) {
        int ans = 0;
        for(int i=1; i<prices.length; i++){
            if(prices[i] > prices[i-1]){
                ans += prices[i] - prices[i-1];
            }
        }
        return ans;
    }

    /**
     * 手撕 只记录min和profit
     */
    public int maxProfit_main(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

}
