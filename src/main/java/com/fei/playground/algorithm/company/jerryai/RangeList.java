package com.fei.playground.algorithm.company.jerryai;

import java.util.Map;
import java.util.TreeMap;

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
     *
     * @param start of range input, inclusive
     * @param end   of range input, exclusive
     */
    public void add(int start, int end) {
        if (start >= end) return;

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
     *
     * @param start of range input, inclusive
     * @param end   of range input, exclusive
     */
    public void remove(int start, int end) {
        if (start >= end) return;

        Map.Entry<Integer, Integer> leftRange = rangesMap.floorEntry(start);
        Map.Entry<Integer, Integer> rightRange = rangesMap.ceilingEntry(start);

        //1. check if the closest left side range removable
        if (leftRange != null && start != leftRange.getKey() && start < leftRange.getValue()) {
            rangesMap.remove(leftRange.getKey());
            if (end < leftRange.getValue()) {
                // 1.1 split the range into 2 parts
                this.add(leftRange.getKey(), start);
                this.add(end, leftRange.getValue());
            } else {
                //1.2 completely remove the range, or partially remove the tail part
                this.add(leftRange.getKey(), start);
            }
        }

        //2. keep checking if the closest right side range removable
        while (rightRange != null && rightRange.getKey() < end) {
            rangesMap.remove(rightRange.getKey());// 2.1 completely remove the range
            if (rightRange.getValue() > end) {
                // 2.2 partially remove the head of the range
                this.add(end, rightRange.getValue());
                break;
            }
            rightRange = rangesMap.ceilingEntry(start);
        }
    }

    /**
     * Prints out the list of ranges in the range list
     */
    public void print() {
        rangesMap.forEach((key, value) -> {
            System.out.printf("[%s,%s)%n", key, value);
        });
    }

    public static void main(String[] args) {
        RangeList r = new RangeList();
        r.add(1, 5);
        r.print();
        System.out.println("-------- End of add[1, 5) --------");

        r.add(10, 20);
        r.print();
        System.out.println("-------- End of add[10, 20) --------");

        r.add(20, 20);
        r.print();
        System.out.println("-------- End of add[20, 20) --------");

        r.add(20, 21);
        r.print();
        System.out.println("-------- End of add[20, 21) --------");

        r.add(2, 4);
        r.print();
        System.out.println("-------- End of add[2, 4) --------");

        r.add(3, 8);
        r.print();
        System.out.println("-------- End of add[3, 8) --------");

        r.remove(10, 10);
        r.print();
        System.out.println("-------- End of remove[10, 10) --------");

        r.remove(10, 11);
        r.print();
        System.out.println("-------- End of remove[10, 11) --------");

        r.remove(15, 17);
        r.print();
        System.out.println("-------- End of remove[15, 17) --------");

        r.remove(3, 19);
        r.print();
        System.out.println("-------- End of remove[3, 19) --------");
    }

}
