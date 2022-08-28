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

    public static int getRandom(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(getRandomWithinRange(3, 15));
        }
    }

}
