package com.fei.playground.algorithm.leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。
 * 半开区间[left, right)表示所有left <= x < right的实数 x 。
 *
 * 实现 RangeModule 类:
 * RangeModule()初始化数据结构的对象。
 * void addRange(int left, int right) 添加 半开区间[left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，应当添加在区间[left, right)中尚未跟踪的任何数字到该区间中。
 * boolean queryRange(int left, int right)只有在当前正在跟踪区间[left, right)中的每一个实数时，才返回 true，否则返回 false 。
 * void removeRange(int left, int right)停止跟踪 半开区间[left, right)中当前正在跟踪的每个实数。
 *
 * https://leetcode.cn/problems/range-module/solution/range-mo-kuai-by-leetcode-solution-4utf/
 */
public class H_Intervals_RangeModule_715 {

    TreeMap<Integer, Integer> intervals;

    public H_Intervals_RangeModule_715() {
        intervals = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry != intervals.firstEntry()) {
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            if (start != null && start.getValue() >= right) {
                return;
            }
            if (start != null && start.getValue() >= left) {
                left = start.getKey();
                intervals.remove(start.getKey());
            }
        }
        while (entry != null && entry.getKey() <= right) {
            right = Math.max(right, entry.getValue());
            intervals.remove(entry.getKey());
            entry = intervals.higherEntry(entry.getKey());
        }
        intervals.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry == intervals.firstEntry()) {
            return false;
        }
        entry = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
        return entry != null && right <= entry.getValue();
    }

    public void removeRange(int left, int right) {
        Map.Entry<Integer, Integer> entry = intervals.higherEntry(left);
        if (entry != intervals.firstEntry()) {
            Map.Entry<Integer, Integer> start = entry != null ? intervals.lowerEntry(entry.getKey()) : intervals.lastEntry();
            if (start != null && start.getValue() >= right) {
                int ri = start.getValue();
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    intervals.put(start.getKey(), left);
                }
                if (right != ri) {
                    intervals.put(right, ri);
                }
                return;
            } else if (start != null && start.getValue() > left) {
                if (start.getKey() == left) {
                    intervals.remove(start.getKey());
                } else {
                    intervals.put(start.getKey(), left);
                }
            }
        }
        while (entry != null && entry.getKey() < right) {
            if (entry.getValue() <= right) {
                intervals.remove(entry.getKey());
                entry = intervals.higherEntry(entry.getKey());
            } else {
                intervals.put(right, entry.getValue());
                intervals.remove(entry.getKey());
                break;
            }
        }
    }

    public void print(){
        intervals.forEach((key, value) -> {
            System.out.printf("[%s,%s)%n", key, value);
        });
    }

    public static void main(String[] args) {
        H_Intervals_RangeModule_715 r = new H_Intervals_RangeModule_715();
        r.addRange(1,5);
//        r.print();

        r.addRange(10,20);
//        r.print();

        r.addRange(20,20);
//        r.print();

        r.addRange(20,21);
        r.addRange(2,4);
//        r.print();

        r.addRange(3,8);
//        r.print();

        r.removeRange(10,10);
//        r.print();

        r.removeRange(10,11);
//        r.print();

        r.removeRange(15,17);
//        r.print();

        r.removeRange(3,19);
        r.print();
    }
}
