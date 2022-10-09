package com.fei.playground.algorithm.leetcode;

public class E_BinarySearch_FirstBadVersion_278 {

    /**
     * 标准简洁的二分法
     * 二分法 不单单是可以找到"相等"的目标
     * 还可以找到"大于/小于某个值的 的分界线、临界点"，它找到的是high"第一个小于等于某临界值的点"
     */
    int firstBadVersion_valid(int n) {
        int lo = 1;
        int hi = n;

        while (lo < hi) {// 循环直至区间左右端点相同 左闭右开
            int mid = lo + (hi - lo) / 2;// 防止计算时溢出
            if (isBadVersion(mid)) {
                hi = mid;//go left 答案在区间[left, mid]中
            } else {
                lo = mid + 1;//go right 答案在区间[mid+1, right]中
            }
        }
        //此时有 left == right，区间缩为一个点，即为答案
        return hi;
    }

    /**
     * 手撕左闭右闭版本，结束时，i和j交错
     */
    public int firstBadVersion_leftRightClose(int n) {
        int lo = 1, hi = n, mid = 0;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if(isBadVersion(mid)){//go left
                hi = mid - 1;
            }else{
                lo = mid + 1;
            }
        }
        return lo;
    }

    /**
     * 击败5% 31% 手撕二分法
     * 今天学到的，不能用（left+right）/2形式，当left和right都是int，
     * 两个值的初始值都超过int限定大小的一半，那么left+right就会发生溢出，
     * 所以应该用left+(right-left)/2来防止求中值时候的溢出。
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n, mid = left + (right - left) / 2;

        //mid==0
        //bad(mid)==true && bad(mid+1)==true --> go left
        //bad(mid)==false && bad(mid+1)==false --> go right
        //bad(mid)==false && bad(mid+1)==true --> return mid+1
        //mid==0 --> return mid
        //bad(mid)==true && bad(mid+1)==false --> 不可能
        while (mid != 0) {
            if (mid == 1 && isBadVersion(1)) {
                return 1;
            } else if (isBadVersion(mid) && isBadVersion(mid + 1)) {
                //go left
                right = mid;
            } else if (!isBadVersion(mid) && !isBadVersion(mid + 1)) {
                //go right
                left = mid;
            } else if (!isBadVersion(mid) && isBadVersion(mid + 1)) {
                return mid + 1;
            }

            mid = left + (right - left) / 2;
        }
        return 1;
    }

    // 下边解法效率不够
    public int firstBadVersion_brute(int n) {
        while (n != 0) {
            if (!isBadVersion(n)) return n + 1;
            n--;
        }
        return 1;
    }

    //mock
    public boolean isBadVersion(int i) {
        return true;
    }
}
