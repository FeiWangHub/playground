package com.fei.playground.algorithm.hackerRank.M_McKinsey_OA;

import java.util.*;

/**
 * Closest Numbers
 * Given an array of distinct integers, determine the minimum
 * absolute difference between any two elements. Print all element
 * pairs with that minimal absolute difference in ascending order.
 * 最小的差的绝对值
 * <p>
 * Example
 * numbers = [6,2,4,10]
 * The minimal absolute difference is 2 and the pairs with that
 * difference are (2,4) and (4,6). When printing element pairs (1)), they
 * should be ordered ascending first by land then by j.
 * 2 4
 * 4 6
 * <p>
 * Function Description
 * Complete the function closestNumbers in the editor below.
 * <p>
 * Prints:
 * distinct element pairs that share the minimal absolute difference,
 * displayed in ascending order with each pair separated by one space
 * on a single line
 * <p>
 * Constraints
 * 1 <= n <= 10^5
 * -2 * 10^6 <= inputs[i] <= 2 * 10^6
 * The numbers array contains no duplicate elements.
 */
public class M_ClosestNumbers {

    /**
     * 手写解法
     */
    public static void closestNumbers(int[] inputs) {
        Arrays.sort(inputs);
        System.out.println(Arrays.toString(inputs));
        LinkedList<Integer> result = new LinkedList<>();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < inputs.length - 1; i++) {
            int absDiff = Math.abs(inputs[i] - inputs[i + 1]);
            if(absDiff > min) continue;;
            if(absDiff < min){
                min = absDiff;
                result.clear();
            }
            result.add(inputs[i]);
            result.add(inputs[i+1]);
        }

        Iterator<Integer> it = result.iterator();
        while (it.hasNext()) {
            StringBuilder b = new StringBuilder();
            System.out.println(b.append(it.next())
                    .append(" ")
                    .append(it.next()));
        }
    }

    /**
     * Hacker Rank变形解法 https://www.hackerrank.com/challenges/closest-numbers/problem
     */
    public static List<Integer> closestNumbers(List<Integer> arr) {
        // Write your code here
        Integer[] inputs = arr.toArray(new Integer[0]);
        Arrays.sort(inputs);
        System.out.println(Arrays.toString(inputs));

        HashMap<Integer, LinkedList<Integer>> min2pairs = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < inputs.length - 1; i++) {
            int absDiff = Math.abs(inputs[i] - inputs[i + 1]);
            min = Math.min(min, absDiff);
            LinkedList<Integer> pair = min2pairs.computeIfAbsent(absDiff, a -> new LinkedList<>());
            pair.add(inputs[i]);
            pair.add(inputs[i + 1]);
            min2pairs.put(absDiff, pair);
        }

        List<Integer> result = min2pairs.get(min);
        return result;
    }

    public static void main(String[] args) {
//        Integer[] arr = {-5, 15, 25, 71, 63};
//        M_ClosestNumbers.closestNumbers(Arrays.asList(arr));

        int[] arr = {6, 2, 4, 10};
//        int[] arr = {-5, 15, 25, 71, 63};
        M_ClosestNumbers.closestNumbers(arr);
    }

}
