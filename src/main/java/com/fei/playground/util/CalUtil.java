package com.fei.playground.util;

import lombok.Data;

import java.util.List;

/**
 * 计算工具类
 * 计算Volatility, Sortino, Sharp
 */
public class CalUtil {

    @Data
    public static class CalResult {

        Double avg;//平均数
        Double vol;//年化波动率
        Double down_vol;//下行波动率
        Double sharp;//sharp率
        Double sortino;//sortino率

        public CalResult(Double avg, Double vol, Double down_vol, Double sharp, Double sortino) {
            this.avg = avg;
            this.vol = vol;
            this.down_vol = down_vol;
            this.sharp = sharp;
            this.sortino = sortino;
        }
    }

    /**
     * 计算年化的Volatility
     * Vol = 样本数据标准差 * sqrt(252)
     * @param samples List<Double> samples
     * @param days 期望结果年化的话，传入252
     * @param rate 无风险利率
     * @return Double
     */
    public static CalResult calVolSharpSortino(List<Double> samples, int days, double rate) {
        int size = samples.size();
        double sum = 0, avg = 0, sum_dev = 0, sum_dev2 = 0, sharp = 0, sortino = 0;

        //计算平均数
        for (Double val : samples) {
            if (val == null) { continue; }
            sum += val;
        }
        avg = size > 0 ? sum / size : 0;

        //计算方差
        for (Double val : samples) {
            if (val == null) { continue; }
            sum_dev += Math.pow((val - avg), 2);
            if (val < avg) {
                sum_dev2 += Math.pow((val - avg), 2);
            }
        }

        //计算波动率
        Double volatility = size > 0 ? Math.sqrt(days * sum_dev / size) : 0; //标准差 年化
        Double volatilityDown = size > 1 ? Math.sqrt(days * sum_dev2 / (size - 1)) : 0;//下行标准差 年化(未年化的sortino?)

        //Sharp & Sortino
        sharp = volatility == 0 ? 0 : (avg * days - rate/100) / volatility;
        sortino = volatilityDown == 0 ? 0 : (avg * days - rate/100) / volatilityDown;

        CalResult r = new CalResult(avg, volatility, volatilityDown, sharp, sortino);
        return r;
    }

}
