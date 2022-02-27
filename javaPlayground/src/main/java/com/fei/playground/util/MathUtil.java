package com.fei.playground.util;

import java.math.BigDecimal;
import java.util.Random;

public class MathUtil {

    /**
     * boundary is inclusive
     */
    public static int getRandomWithinRange(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max-min+1)+min;
    }

    public static Double toFix2(Double amount){
        return toFix(amount,2);
    }
    public static Double toFix(Double amount,int num){
        return new BigDecimal(amount)
                .setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
