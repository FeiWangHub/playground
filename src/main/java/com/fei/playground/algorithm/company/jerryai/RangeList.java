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
     * TODO 入参效验
     *
     * @param start inclusive
     * @param end   exclusive
     */
    public void add(int start, int end) {
        if (start >= end) return;

        //5种情况
        //1.独立新区间
        //2.与前面区间相连
        //3.与后边区间相连
        //4.与前后都相连
        //5.包含了一个或多个现有区间

        Map.Entry<Integer, Integer> ceilingEntry = rangesMap.ceilingEntry(start);
        Map.Entry<Integer, Integer> floorEntry = rangesMap.floorEntry(start);
        System.out.println(String.format("ceilingEntry: %s, floorEntry: %s;", ceilingEntry, floorEntry));

        //1.最大的新区间
//        if (ceilingEntry == null) {//
//            rangesMap.put(start, end);
//            return;
//        }

        //现有区间 TODO ceiling是不是也要检查
        if (floorEntry != null
                && floorEntry.getKey() <= start
                && floorEntry.getValue() >= end) {
            return;
        }

        //2.检查floorEntry是否有mergable，如果有，merge
        if (floorEntry != null && start <= floorEntry.getValue()) {
            start = floorEntry.getKey();
            rangesMap.remove(floorEntry.getKey());
        }

        //3.持续向后检查ceiling是否可以merge
        while (ceilingEntry != null && ceilingEntry.getKey() <= end) {//有overlap
            end = Math.max(end, ceilingEntry.getValue());
            rangesMap.remove(ceilingEntry.getKey());
            ceilingEntry = rangesMap.ceilingEntry(start);
        }

        rangesMap.put(start, end);
    }

    /**
     * TODO 入参效验
     *
     * @param start start of range, inclusive
     * @param end   end of range, exclusive
     */
    public void remove(int start, int end) {

    }

    public void print() {
        rangesMap.forEach((key, value) -> {
            System.out.printf("[%s,%s)%n", key, value);
        });
        System.out.println("----End---");
    }

    public static void main(String[] args) {
        RangeList r = new RangeList();
        r.add(1, 5);
        r.add(1, 5);
        r.print();

        r.add(10, 20);
        r.print();

        r.add(20, 20);
        r.print();

        r.add(20, 21);
        r.print();

        r.add(2, 4);
        r.print();

        r.add(3, 8);
        r.print();

//        r.remove(10, 10);
//        r.print();

//        r.remove(10, 11);
//        r.print();

//        r.remove(15, 17);
//        r.print();

//        r.remove(3, 19);
//        r.print();
    }

}
