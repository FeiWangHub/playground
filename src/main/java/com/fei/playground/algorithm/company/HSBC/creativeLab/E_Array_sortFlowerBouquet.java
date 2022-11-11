package com.fei.playground.algorithm.company.HSBC.creativeLab;

import java.util.Arrays;

/**
 * Emma wishes to give her father a
 * bouquet for his birthday. She asks
 * for help from her mother Rosy. Rosy
 * gives N flower sticks numbered 1 to
 * N to Emma and tells her to arrange
 * them in the bouquet in a particular
 * order. She asks Emma to arrange the
 * first K flower sticks in the order of
 * increasing length and the remaining
 * sticks in the order of decreasing
 * length.
 * Write an algorithm to find the final
 * arrangement of the flower sticks in
 * the bouquet.
 *
 * Input
 * The first line of the input consists of
 * an integer - flowerStick size,
 * representing the number of flower
 * sticks (N)
 * The second line consists of N space-
 * separated integers- flowerStick[1],
 * flowerStick[2]……flowerStick[N], repre
 * the length of the flower sticks.
 * The last line consists of an integer -
 * random, representing the number K
 * given by Rosy to Emma
 */
public class E_Array_sortFlowerBouquet {

    //Q1
    public static int[]  funcBouquet(int[] flowerStick, int random)
    {
        int len = flowerStick.length;
        int[] answer = new int[len];
        // Write your code here

        int[] firstK = Arrays.copyOfRange(flowerStick, 0, random);
        Arrays.sort(firstK);
        for(int i=0; i<random; i++){
            answer[i] = firstK[i];
        }

        int[] rest = Arrays.copyOfRange(flowerStick, random, len);
        Arrays.sort(rest);

        int restIdx = rest.length - 1;
        for(int i=random; i<len; i++){
            answer[i] = rest[restIdx];
            restIdx--;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] ans = funcBouquet(new int[]{7,43,12,4,1,3,78,6}, 4);
        System.out.println(Arrays.toString(ans));
    }

}
