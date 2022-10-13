package com.fei.playground.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * 658. 找到 K 个最接近的元素
 * 给定一个 排序好 的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。
 * 返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * https://leetcode.cn/problems/find-k-closest-elements/
 */
public class M_Array_BinarySearch_findKClosestItem_658 {

    /**
     * 手撕 二分法 38% 67%
     * 时间logN+k 空间1
     * 还有大量改进空间
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> res = new LinkedList<>();

        //1.暴力破解，1个循环，计算每个数字与k的距离，并记录在map中；
        //1.1 对结果排序，最小的K个就是答案；答案记得排序（解法缺点是没有利用上现有排序）

        //2 二分查找，找到距离x最近的位置(左边小于x，右边大于x); 中央扩张，找到k个数字；结果排序
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while (left < right) {//左闭右开区间,循环直至区间左右端点相同
            mid = left + (right - left) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        //mid是最接近x的点idx
        System.out.println("mid index: " + left);

        right = left;
        left = right - 1;
        while (k-- != 0) {
            if (left < 0) {
                res.addLast(arr[right]);
                right++;
            } else if (right >= arr.length) {
                res.addFirst(arr[left]);
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                res.addFirst(arr[left]);
                left--;
            } else {
                res.addLast(arr[right]);
                right++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));
//        System.out.println(findClosestElements(new int[]{0, 0, 0, 1, 3, 5, 6, 7, 8, 8}, 2, 2));
//        System.out.println(findClosestElements(new int[]{1, 3}, 1, 2));
//        System.out.println(findClosestElements(new int[]{1, 10, 15, 25, 35, 45, 50, 59}, 1, 30));
    }

}
