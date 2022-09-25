package com.fei.playground.algorithm.company.jerryai;

import java.util.Map;
import java.util.TreeMap;

/**
 * A pair of integers define a range, for example: [1, 5). This range includes integers: 1, 2, 3, and 4.
 * A range list is an aggregate of these ranges: [1, 5), [10, 11), [100,201)
 */
public class RangeList {

    /**
     * A treemap, representing all current ranges
     * There is no mergable ranges at all time
     * Key: start of range, inclusive
     * Value: end of range, exclusive
     */
    TreeMap<Integer, Integer> rangesMap;

    public RangeList() {
        this.rangesMap = new TreeMap<>();
    }

    /**
     * Adds a range to the list
     * (According to requirement, the input should be Array<number>)
     *
     * @param range int[], range[0] is start of range, inclusive; range[1] is end of range, exclusive.
     */
    public void add(int[] range) {
        if (!isValidInput(range)) return;
        int start = range[0];
        int end = range[1];

        Map.Entry<Integer, Integer> leftRange = rangesMap.floorEntry(start);
        Map.Entry<Integer, Integer> rightRange = rangesMap.ceilingEntry(start);

        //1. if input range already contained in current rangesMap
        if (leftRange != null && leftRange.getKey() <= start && leftRange.getValue() >= end) return;

        //2. Merge with the closest left side range if mergable
        if (leftRange != null && start <= leftRange.getValue()) {
            start = leftRange.getKey();
            rangesMap.remove(leftRange.getKey());
        }

        //3. Keep merging the closest right side range if mergable
        while (rightRange != null && rightRange.getKey() <= end) {//有overlap
            end = Math.max(end, rightRange.getValue());
            rangesMap.remove(rightRange.getKey());
            rightRange = rangesMap.ceilingEntry(start);
        }

        rangesMap.put(start, end);
    }

    /**
     * Removes a range from the list
     * (According to requirement, the input should be Array<number>)
     *
     * @param range int[], range[0] is start of range, inclusive; range[1] is end of range, exclusive.
     */
    public void remove(int[] range) {
        if (!isValidInput(range)) return;
        int start = range[0];
        int end = range[1];

        Map.Entry<Integer, Integer> leftRange = rangesMap.floorEntry(start);
        Map.Entry<Integer, Integer> rightRange = rangesMap.ceilingEntry(start);

        //1. check if the closest left side range removable
        if (leftRange != null && start != leftRange.getKey() && start < leftRange.getValue()) {
            rangesMap.remove(leftRange.getKey());
            if (end < leftRange.getValue()) {
                // 1.1 split the range into 2 parts
                this.add(new int[]{leftRange.getKey(), start});
                this.add(new int[]{end, leftRange.getValue()});
            } else {
                //1.2 completely remove the range, or partially remove the tail part
                this.add(new int[]{leftRange.getKey(), start});
            }
        }

        //2. keep checking if the closest right side range removable
        while (rightRange != null && rightRange.getKey() < end) {
            rangesMap.remove(rightRange.getKey());// 2.1 completely remove the range
            if (rightRange.getValue() > end) {
                // 2.2 partially remove the head of the range
                this.add(new int[]{end, rightRange.getValue()});
                break;
            }
            rightRange = rangesMap.ceilingEntry(start);
        }
    }

    /**
     * validate input for Add and Remove function
     *
     * @param inputRange expect int[] of length 2
     * @return true if inputRange is of length 2, and start index inputRange[0] is less than inputRange[1]
     */
    public boolean isValidInput(int[] inputRange) {
        return inputRange.length == 2 && inputRange[0] < inputRange[1];
    }

    /**
     * Prints and return the list of ranges in the range list
     * Prints and return empty string if no ranges in list
     *
     * @return example "[1,5) [10,20)"
     */
    public String print() {
        if (rangesMap.size() == 0) {
            System.out.println();
            return "";
        }

        StringBuilder strBuilder = new StringBuilder();
        rangesMap.forEach((key, value) -> {
            strBuilder.append(String.format("[%s,%s) ", key, value));
        });
        strBuilder.deleteCharAt(strBuilder.length() - 1);
        System.out.println(strBuilder);
        return strBuilder.toString();
    }
}
