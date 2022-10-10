package com.fei.playground.algorithm.company.jerryai;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 第一轮coding：
 * 应该算是一道easy-medium，给定一个数组，只有一个初始数字1，对这个数组的每个数字k，做k*2+1和k*3+1，然后加入数组，
 * 要求这个数组是sorted并且没有重复元素，返回第N个
 * 这个数组应该是[1,3,4,7,9,10,13,....]
 * 算法
 * 3(1*2+1), 4(1*3+1)
 * 7(3*2+1), 10(3*3+1)
 * 9(4*2+1), 13(4*3+1)
 * ...(下一轮的顺序，是7-9-10-13，还是7-10-9-13? 生成顺序，还是排序顺序？假设是7-9-10-13吧，按照大小顺序)
 * 因为出现了3算出来的比4还大，所以单纯用queue不行，要用heap，然后用set去重
 * 分析了时间空间复杂度。
 * CSDN 面经 https://blog.csdn.net/xnninger/article/details/115682070
 * 一亩三分地 https://www.1point3acres.com/bbs/thread-900233-1-1.html
 */
public class M_Array_JerryAI_ModifyAndSort {

    /**
     * 复杂度N*logN
     */
    public static int question(int[] arr) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();//heap里存的是尚未处理，且去重的数字
        HashSet<Integer> set = new HashSet<>();//数组中已经包含的数字
        heap.add(1);
        set.add(1);

        int idx = 0;
        while (true) {
            int k = heap.poll();
            arr[idx++] = k;
            if (idx == arr.length) break;//到达第n个

            int k1 = k * 2 + 1;
            if (!set.contains(k1)) {
                set.add(k1);
                heap.add(k1);
            }

            int k2 = k * 3 + 1;
            if (!set.contains(k2)) {
                set.add(k2);
                heap.add(k2);
            }
        }

        System.out.println(Arrays.toString(arr));
        return arr[arr.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(question(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0}));//[1,3,4,7,9,10,13,....]
    }

}
