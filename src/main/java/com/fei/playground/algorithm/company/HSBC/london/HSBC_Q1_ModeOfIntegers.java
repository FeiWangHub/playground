package com.fei.playground.algorithm.company.HSBC.london;

import java.text.SimpleDateFormat;
import java.util.*;

//    Write a simple application which, when given a list of integers:
//    - Calculates and prints out the mode of the distribution (most frequent element)
//    - Prints a bar chart showing the frequency of each element. For the sake of simplicity,
//      the bar chart can be horizontal i.e. with the number on the y axis, and frequency on the x axis.
//      Although a nice to have, the y axis doesn't have to be ordered.
//
//    Consider the time and space complexity of your solution,aiming for one that is O(N) in both cases,
//      where N is the size of the input collection.
//    Initial signature: void analyse(Collection<Integer> values);
//
//    Example:
//    input: [1,3,3,1,4,3,3]
//    output:
//    mode=3
//            1 | **
//            3 | ****
//            4 | *
public class HSBC_Q1_ModeOfIntegers {

    public static void main(String[] args) {
        System.out.printf("---- Started, time now is %s ----%n", new SimpleDateFormat("HH:mm:ss:SSS").format(new Date()));
        analyse(Arrays.asList(1, 3, 5, 3, 1, 4, 3, 3));
        //        analyse(Arrays.asList());
        //        analyse(Arrays.asList(-1));
        //        analyse(Arrays.asList(Integer.MAX_VALUE));
        //        analyse(null);
    }

    public static void analyse(Collection<Integer> values) {
        //part 1
        int maxCount = Integer.MIN_VALUE;
        int maxInt = Integer.MIN_VALUE;
        int tempCount = 0;
        HashMap<Integer, Integer> int2countMap = new HashMap<>();

        Iterator<Integer> it = values.iterator();
        while (it.hasNext()) {
            int i = it.next();
            tempCount = int2countMap.getOrDefault(i, 0) + 1;
            if (tempCount > maxCount) {
                maxCount = tempCount;
                maxInt = i;
            }
            int2countMap.put(i, tempCount);
        }

        System.out.println("mode of the distribution Integer: " + maxInt + " count:" + maxCount);

        int2countMap.forEach((k, v) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(k).append(" | ");
            for (int i = 0; i < v; i++) {
                sb.append("*");
            }
            System.out.println(sb.toString());
        });
    }

}
