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

        Map.Entry<Integer, Integer> leftRange = rangesMap.floorEntry(start);
        Map.Entry<Integer, Integer> rightRange = rangesMap.ceilingEntry(start);
        System.out.println(String.format("rightRange: %s, leftRange: %s;", rightRange, leftRange));

        //1. Range to add already contained in previous range
        if (leftRange != null && leftRange.getKey() <= start && leftRange.getValue() >= end) return;

        //2. Merge with left side range if mergable
        if (leftRange != null && start <= leftRange.getValue()) {
            start = leftRange.getKey();
            rangesMap.remove(leftRange.getKey());
        }

        //3.持续向后检查ceiling是否可以merge
        while (rightRange != null && rightRange.getKey() <= end) {//有overlap
            end = Math.max(end, rightRange.getValue());
            rangesMap.remove(rightRange.getKey());
            rightRange = rangesMap.ceilingEntry(start);
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
        if (start >= end) return;

        Map.Entry<Integer, Integer> leftRange = rangesMap.floorEntry(start);
        Map.Entry<Integer, Integer> rightRange = rangesMap.ceilingEntry(start);
        System.out.println(String.format("rightRange: %s, leftRange: %s;", rightRange, leftRange));

        //1 一个区间被完整移除
        //2 一个区间被后半部分、前半部分、中间部分被移除
        //3 没有任何移除
        if (leftRange != null && start != leftRange.getKey() && start < leftRange.getValue()) {
            rangesMap.remove(leftRange.getKey());
            if (end < leftRange.getValue()) {
                // 1 从中间 把一个区间瓜分两半
                this.add(leftRange.getKey(), start);
                this.add(end, leftRange.getValue());
            } else {
                //2 左边部分删除尾部 || 完全删除
                this.add(leftRange.getKey(), start);
            }
        }

        //向右寻找
        while (rightRange != null && rightRange.getKey() < end) {
            rangesMap.remove(rightRange.getKey());// 3.1 右边完全删除
            if (rightRange.getValue() > end) {
                // 3.2 右边部分删除
                this.add(end, rightRange.getValue());
                break;
            }
            rightRange = rangesMap.ceilingEntry(start);
        }
    }

    public void print() {
        rangesMap.forEach((key, value) -> {
            System.out.printf("[%s,%s)%n", key, value);
        });
        System.out.println("----End---");//TODO delete
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

        r.remove(10, 10);
        r.print();

        r.remove(10, 11);
        r.print();

        r.remove(15, 17);
        r.print();

        r.remove(3, 19);
        r.print();
    }

}
