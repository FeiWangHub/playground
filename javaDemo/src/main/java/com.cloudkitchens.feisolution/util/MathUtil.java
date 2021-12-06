package com.cloudkitchens.feisolution.util;

import java.util.Random;

public class MathUtil {

    /**
     * boundary is inclusive
     */
    public static int getRandomWithinRange(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max-min+1)+min;
    }

}
